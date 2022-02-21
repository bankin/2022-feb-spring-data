package entities;

import annotations.Column;
import annotations.Entity;
import annotations.Id;

@Entity(name = "addresses")
public class Address {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "street")
    private String street;

    @Column(name = "street_number")
    private int streetNumber;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String county;

    @Column(name = "postal_code")
    private String postalCode;

    public Address() {}

    public Address(String street, int streetNumber, String city, String county, String postalCode) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
        this.county = county;
        this.postalCode = postalCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", streetNumber=" + streetNumber +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
