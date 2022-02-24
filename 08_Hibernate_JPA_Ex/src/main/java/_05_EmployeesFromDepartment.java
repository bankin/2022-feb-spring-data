import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class _05_EmployeesFromDepartment {

    public static void main(String[] args) {
        EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        String department = "Research and Development";

        entityManager
            .createQuery("SELECT e FROM Employee e" +
                " WHERE e.department.name = :departmentName" +
                " ORDER BY e.salary ASC, e.id ASC",
                Employee.class)
            .setParameter("departmentName", department)
            .getResultStream()
            .forEach(e -> {
                String format = String.format("%s %s from %s - $%.2f",
                    e.getFirstName(), e.getLastName(), department, e.getSalary());

                System.out.println(format);
            });

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
