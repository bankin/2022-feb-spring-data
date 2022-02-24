import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class _06_InsertAddress {

    public static void main(String[] args) {
        EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);

        String addressText = "Vitoshka 15";
        Address address = new Address();
        address.setText(addressText);
        entityManager.persist(address);

        String lastName = scanner.nextLine();

        entityManager
            .createQuery("UPDATE Employee e" +
                            " SET e.address = :address" +
                            " WHERE e.lastName = :name")
            .setParameter("name", lastName)
            .setParameter("address", address)
            .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
