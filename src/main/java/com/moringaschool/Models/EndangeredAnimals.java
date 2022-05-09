package com.moringaschool.Models;

import java.util.ArrayList;

public class EndangeredAnimals {
    private int id;
    private String name;
    private String health;
    public static ArrayList<EndangeredAnimals> instances;

    public EndangeredAnimals(String name, String health) {
        this.health = health;
        this.name = name;
        instances.add(this);
        this.id = getInstances().size();
    }

    public static ArrayList<EndangeredAnimals> getInstances() {
        return instances;
    }

    public String getName() {
        return name;
    }

    public String getHealth() {
        return health;
    }
}
