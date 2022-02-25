package entities.shampoo;

import javax.persistence.*;

@Entity(name = "ingredients")
public class BasicIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double quantity;

    @Column(nullable = false)
    private String chemicalName;

    public BasicIngredient() {
    }

    public BasicIngredient(double quantity, String chemicalName) {
        this.quantity = quantity;
        this.chemicalName = chemicalName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getChemicalName() {
        return chemicalName;
    }

    public void setChemicalName(String chemicalName) {
        this.chemicalName = chemicalName;
    }
}
