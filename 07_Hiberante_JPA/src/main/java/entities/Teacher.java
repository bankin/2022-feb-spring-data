package entities;

import org.hibernate.annotations.ListIndexBase;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "started_at")
    private LocalDate startedAt;

    @Transient
    private int numberOfStudents;

    public Teacher () {}

    public Teacher(String name, LocalDate startedAt) {
        this.name = name;
        this.startedAt = startedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDate startedAt) {
        this.startedAt = startedAt;
    }
}
