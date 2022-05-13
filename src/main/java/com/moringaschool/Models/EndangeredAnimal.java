package com.moringaschool.Models;

import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EndangeredAnimal {
    private int id;
    private int animal_id;
    private String animal_name;
    private String health;
    private int age;

    public EndangeredAnimal(String animal_name, int animal_id, String health, int age) {
        this.animal_id = animal_id;
        this.animal_name = animal_name;
        this.health = health;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EndangeredAnimal)) return false;
        EndangeredAnimal that = (EndangeredAnimal) o;
        return getId() == that.getId() && getAnimal_id() == that.getAnimal_id() && getAge() == that.getAge() && getAnimal_name().equals(that.getAnimal_name()) && getHealth().equals(that.getHealth());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAnimal_id(), getAnimal_name(), getHealth(), getAge());
    }

    public String getAnimal_name() {
        return animal_name;
    }

    public String getHealth() {
        return health;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getAnimal_id() {
        return animal_id;
    }
}
