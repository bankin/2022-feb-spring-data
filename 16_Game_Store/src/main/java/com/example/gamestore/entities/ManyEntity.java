package com.example.gamestore.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ManyEntity {
    @Id
    private String uuid;

    private String name;

    private int age;

    public ManyEntity() {
    }

    public ManyEntity(String name, int age) {
        this();

        this.name = name;
        this.age = age;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String id) {
        this.uuid = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
