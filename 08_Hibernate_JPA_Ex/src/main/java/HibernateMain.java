import entities.Town;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateMain {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session currentSession = sessionFactory.openSession();
        currentSession.beginTransaction();

        Town town = currentSession.get(Town.class, 1);
        System.out.println(town);

        currentSession.getTransaction().commit();
    }
}
