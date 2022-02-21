package orm;

import annotations.Column;
import annotations.Entity;
import annotations.Id;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class EntityManager<E> implements DBContext<E> {
    private final Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    public void doCreate(Class<E> entityClass) throws SQLException {
        String tableName = getTableName(entityClass);
        String fieldsWithTypes = getSQLFieldsWithTypes(entityClass);

        String createQuery = String.format("CREATE TABLE %s (" +
                        "id INT PRIMARY KEY AUTO_INCREMENT, %s)",
                tableName, fieldsWithTypes);

        PreparedStatement statement = connection.prepareStatement(createQuery);
        statement.execute();
    }

    public void doAlter(Class<E> entityClass) throws SQLException {
        String tableName = getTableName(entityClass);
        String addColumnStatements = getAddColumnStatementsForNewFields(entityClass);

        String alterQuery = String.format("ALTER TABLE %s %s", tableName, addColumnStatements);

        PreparedStatement statement = connection.prepareStatement(alterQuery);
        statement.execute();
    }

    @Override
    public boolean persist(E entity) throws IllegalAccessException, SQLException {
        Field idColumn = getIdColumn(entity.getClass());
        Object idValue = getFieldValue(entity, idColumn);

        if (idValue == null || (long) idValue <= 0) {
            return doInsert(entity);
        }

        return doUpdate(entity, (long) idValue);
    }

    @Override
    public Iterable<E> find(Class<E> table) throws SQLException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return find(table, null);
    }

    @Override
    public Iterable<E> find(Class<E> table, String where) throws SQLException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return baseFind(table, where, null);
    }

    @Override
    public E findFirst(Class<E> table) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return findFirst(table, null);
    }

    @Override
    public E findFirst(Class<E> table, String where) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<E> result = baseFind(table, where, "LIMIT 1");

        return result.get(0);
    }

    @Override
    public boolean delete(E toDelete) throws IllegalAccessException, SQLException {
        String tableName = getTableName(toDelete.getClass());

        Field idColumn = getIdColumn(toDelete.getClass());
        String idColumnName = getSQLColumnName(idColumn);
        Object idColumnValue = getFieldValue(toDelete, idColumn);

        String query = String.format("DELETE FROM %s WHERE %s = %s",
                tableName, idColumnName, idColumnValue);

        PreparedStatement statement = connection.prepareStatement(query);

        return statement.execute();
    }

    private boolean doInsert(E entity) throws SQLException, IllegalAccessException {
        String tableName = getTableName(entity.getClass());
        List<String> tableFields = getColumnsWithoutId(entity.getClass()); // username, age, registration_date
        List<String> tableValues = getColumnsValuesWithoutId(entity);

        String insertQuery = String.format("INSERT INTO %s (%s) VALUES (%s)",
                tableName,
                String.join(",", tableFields),
                String.join(",", tableValues));

        return connection.prepareStatement(insertQuery).execute();
    }

    private boolean doUpdate(E entity, long idValue) throws SQLException, IllegalAccessException {
        String tableName = getTableName(entity.getClass());
        List<String> tableFields = getColumnsWithoutId(entity.getClass()); // username, age, registration_date
        List<String> tableValues = getColumnsValuesWithoutId(entity);

        List<String> setStatements = new ArrayList<>();
        for (int i = 0; i < tableFields.size(); i++) {
            String statement = tableFields.get(i) + " = " + tableValues.get(i);

            setStatements.add(statement);
        }

        String updateQuery = String.format("UPDATE %s SET %s WHERE id = %d",
                tableName,
                String.join(",", setStatements),
                idValue);

        PreparedStatement statement = connection.prepareStatement(updateQuery);
        return statement.execute();
    }

    private List<E> baseFind(Class<E> table, String where, String limit) throws SQLException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String tableName = getTableName(table);

        String selectQuery = String.format("SELECT * FROM %s %s %s",
                tableName,
                where != null ? "WHERE " + where : "",
                limit != null ? limit : "");

        PreparedStatement statement = connection.prepareStatement(selectQuery);
        ResultSet resultSet = statement.executeQuery();

        List<E> result = new ArrayList<>();
        while (resultSet.next()) {
            E entity = table.getDeclaredConstructor().newInstance();
            fillEntity(table, resultSet, entity);

            result.add(entity);
        }

        return result;
    }

    private void fillEntity(Class<E> table, ResultSet resultSet, E entity) throws SQLException, IllegalAccessException {
        Field[] declaredFields = table.getDeclaredFields();

        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            fillFiled(declaredField, resultSet, entity);
        }
    }

    private void fillFiled(Field declaredField, ResultSet resultSet, E entity) throws SQLException, IllegalAccessException {
        Class<?> fieldType = declaredField.getType();
        String fieldName = getSQLColumnName(declaredField);

        if (fieldType == int.class || fieldType == Integer.class) {
            int value = resultSet.getInt(fieldName);

            declaredField.set(entity, value);
        } else if (fieldType == long.class || fieldType == Long.class) {
            long value = resultSet.getLong(fieldName);

            declaredField.set(entity, value);
        } else if (fieldType == LocalDate.class) {
            LocalDate value = LocalDate.parse(resultSet.getString(fieldName));

            declaredField.set(entity, value);
        } else {
            String value = resultSet.getString(fieldName);

            declaredField.set(entity, value);
        }
    }

    private Field getIdColumn(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
            .filter(f -> f.isAnnotationPresent(Id.class))
            .findFirst()
            .orElseThrow(() ->
                new UnsupportedOperationException("Entity missing an Id column"));
    }

    private List<String> getColumnsValuesWithoutId(E entity) throws IllegalAccessException {
        List<Field> fields = getEntityColumnFieldsWithoutId(entity.getClass());

        List<String> values = new ArrayList<>();
        for (Field field : fields) {
            Object o = getFieldValue(entity, field);

            if (o instanceof String || o instanceof LocalDate) {
                values.add("'" + o + "'");
            } else {
                values.add(o.toString());
            }
        }

        return values;
    }

    private List<Field> getEntityColumnFieldsWithoutId(Class<?> aClass) {
        return Arrays.stream(aClass.getDeclaredFields())
                .filter(f -> !f.isAnnotationPresent(Id.class))
                .filter(f -> f.isAnnotationPresent(Column.class))
                .collect(Collectors.toList());
    }

    private List<String> getColumnsWithoutId(Class<?> aClass) {
        return getEntityColumnFieldsWithoutId(aClass)
                .stream()
                .map(this::getSQLColumnName)
                .collect(Collectors.toList());
    }

    private String getSQLFieldsWithTypes(Class<E> entityClass) {
        return getEntityColumnFieldsWithoutId(entityClass)
            .stream()
            .map(field -> {
                String fieldName = getSQLColumnName(field);
                String sqlType = getSQLType(field.getType());

                return fieldName + " " + sqlType;
            })
            .collect(Collectors.joining(","));
    }

    private String getAddColumnStatementsForNewFields(Class<E> entityClass) throws SQLException {
        Set<String> sqlColumns = getSQLColumnNames(entityClass);
        List<Field> fields = getEntityColumnFieldsWithoutId(entityClass);

        List<String> allAddStatements = new ArrayList<>();
        for (Field field : fields) {
            String fieldName = getSQLColumnName(field);

            if (sqlColumns.contains(fieldName)) {
                continue;
            }

            String sqlType = getSQLType(field.getType());

            String addStatement = String.format("ADD COLUMN %s %s", fieldName, sqlType);
            allAddStatements.add(addStatement);
        }

        return String.join(",", allAddStatements);
    }

    private Set<String> getSQLColumnNames(Class<E> entityClass) throws SQLException {
        String tableName = getTableName(entityClass);
        Field idColumn = getIdColumn(entityClass);
        String idColumnName = getSQLColumnName(idColumn);

        String schemaQuery = "SELECT COLUMN_NAME FROM information_schema.`COLUMNS` c" +
                " WHERE c.TABLE_SCHEMA = 'custom-orm'" +
                " AND COLUMN_NAME != '%s'" +
                " AND TABLE_NAME = '%s';";

        PreparedStatement statement = connection.prepareStatement(String.format(schemaQuery, tableName, idColumnName));

        ResultSet resultSet = statement.executeQuery();

        Set<String> result = new HashSet<>();
        while(resultSet.next()) {
            String columnName = resultSet.getString("COLUMN_NAME");

            result.add(columnName);
        }

        return result;
    }

    private String getSQLType(Class<?> type) {
        String sqlType = "";
        if (type == Integer.class || type == int.class) {
            sqlType = "INT";
        } else if (type == String.class) {
            sqlType = "VARCHAR(200)";
        } else if (type == LocalDate.class) {
            sqlType = "DATE";
        }

        return sqlType;
    }

    private String getTableName(Class<?> aClass) {
        Entity[] annotationsByType = aClass.getAnnotationsByType(Entity.class);

        if (annotationsByType.length == 0) {
            throw new UnsupportedOperationException("Class must be Entity");
        }

        return annotationsByType[0].name();
    }

    private Object getFieldValue(E entity, Field idColumn) throws IllegalAccessException {
        idColumn.setAccessible(true);

        return idColumn.get(entity);
    }

    private String getSQLColumnName(Field idColumn) {
        return idColumn.getAnnotationsByType(Column.class)[0].name();
    }
}
