import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("CodeFirstEx");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        User user = new User("marina", AccountType.PLATINUM);
        entityManager.persist(user);

        User found = entityManager.find(User.class, 1);

        System.out.println(found.getType().toString());

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
