import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

public class _02_ChangeCasing {

    public static void main(String[] args) {
        EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        // entityManager.createQuery("UPDATE Town t SET t.name = lower(t.name) " +
        //                "WHERE length(t.name) >= 5");
        Query from_town = entityManager
                .createQuery("SELECT t FROM Town t", Town.class);
        List<Town> resultList = from_town.getResultList();

        for (Town town : resultList) {
            String name = town.getName();

            if (name.length() <= 5) {
                String toUpper = name.toUpperCase();
                town.setName(toUpper);

                entityManager.persist(town);
            }
        }

        entityManager.getTransaction().commit();
    }
}
