package entities.vehicle;

import javax.persistence.*;

@Entity
@DiscriminatorValue("bike")
public class Bike extends Vehicle {
    private static final String BIKE_TYPE = "Bike";

    private int gearsCount;

    public Bike() {}

    public Bike(int gearsCount) {
        super(BIKE_TYPE, 250);
        this.gearsCount = gearsCount;
    }

    public static String getBikeType() {
        return BIKE_TYPE;
    }

    public int getGearsCount() {
        return gearsCount;
    }

    public void setGearsCount(int gearsCount) {
        this.gearsCount = gearsCount;
    }
}
