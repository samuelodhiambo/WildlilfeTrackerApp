package com.moringaschool.Models;

import java.util.ArrayList;
import java.util.Objects;

public class Animal {
    private int id;
    private String name;
    public static ArrayList<Animal> instances;

    public Animal(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return getId() == animal.getId() &&
                Objects.equals(getName(), animal.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getId());
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static ArrayList<Animal> getInstances() {
        return instances;
    }

    public void setId(int id) {
        this.id = id;
    }
}
