import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class _04_RichEmployeesFirstNames {

    public static void main(String[] args) {
        EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        List<String> resultList = entityManager
                .createQuery("SELECT e.firstName FROM Employee e" +
                        " WHERE e.salary > 50000",
                        String.class)
                .getResultList();

        String result = String.join("\n", resultList);
        System.out.println(result);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
