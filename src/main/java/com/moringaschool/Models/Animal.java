package com.moringaschool.Models;

import java.util.ArrayList;

public class Animal {
    private int id;
    private String name;
    public static ArrayList<Animal> instances;

    public Animal(String name) {
        this.name = name;
        instances.add(this);
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
