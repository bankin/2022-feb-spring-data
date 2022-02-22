import entities.Student;
import entities.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("school");

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Student student = new Student("emo", 22);
        Student second = new Student("gosho", 45);
        entityManager.persist(student);
        entityManager.persist(second);

        Student first = entityManager.find(Student.class, 1);
        entityManager.remove(first);

        Teacher teacher = new Teacher("petka", LocalDate.now());
        entityManager.persist(teacher);

        teacher.setName("gergana");
        entityManager.persist(teacher);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
