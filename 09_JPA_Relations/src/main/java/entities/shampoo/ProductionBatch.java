package entities.shampoo;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.PropertyPermission;
import java.util.Set;

@Entity(name = "batches")
public class ProductionBatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate createdAt;

    @OneToMany(mappedBy = "batch", targetEntity = BasicShampoo.class,
        cascade = CascadeType.ALL)
    private Set<BasicShampoo> shampoos;

    public ProductionBatch() {}

    public ProductionBatch(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Set<BasicShampoo> getShampoos() {
        return shampoos;
    }

    public void setShampoos(Set<BasicShampoo> shampoos) {
        this.shampoos = shampoos;
    }
}
