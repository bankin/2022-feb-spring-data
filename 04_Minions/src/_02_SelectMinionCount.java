import java.sql.*;
import java.util.Properties;

public class _02_SelectMinionCount {

    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3307/minions_db", properties);

        PreparedStatement statement = connection.prepareStatement(
                "SELECT name, COUNT(distinct mv.minion_id) as minion_count from villains as v" +
                " JOIN minions_villains as mv on mv.villain_id = v.id" +
                " GROUP BY mv.villain_id" +
                " HAVING minion_count > ?" +
                " ORDER BY minion_count desc;");

        statement.setInt(1, 15);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String villainName = resultSet.getString("name");
            int minionCount = resultSet.getInt("minion_count");

            System.out.println(villainName + " " + minionCount);
        }

        connection.close();
    }
}
