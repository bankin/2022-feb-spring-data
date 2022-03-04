import entities.Department;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;

public class _10_IncreaseSalaries {
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        List<String> departmentNames = Arrays.asList(
                "Engineering",
                "Tool Design",
                "Marketing",
                "Information Services");

        List<Department> departments = entityManager.createQuery(
            "FROM Department d WHERE d.name IN (:names)",
                    Department.class)
            .setParameter("names", departmentNames)
            .getResultList();

        entityManager
            .createQuery("UPDATE Employee e" +
                " SET e.salary = e.salary * 1.12" +
                " WHERE e.department IN (:departments)")
            .setParameter("departments", departments)
            .executeUpdate();

        // Fetch users in departments & print info

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
