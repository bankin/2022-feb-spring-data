import entities.Bike;
import entities.Car;
import entities.Truck;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Bike bike = new Bike(21);
        Car car = new Car(5);
        Truck truck = new Truck(25000, 40000);


        entityManager.persist(bike);
        entityManager.persist(car);
        entityManager.persist(truck);

        entityManager.getTransaction().commit();
    }
}
