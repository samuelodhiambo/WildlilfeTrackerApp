package com.moringaschool.Models;

import java.util.ArrayList;

public class EndangeredAnimal {
    private int id;
    private String name;
    private String health;
    public static ArrayList<EndangeredAnimal> instances;

    public EndangeredAnimal(String name, String health) {
        this.health = health;
        this.name = name;
        instances.add(this);
    }

    public static ArrayList<EndangeredAnimal> getInstances() {
        return instances;
    }

    public String getName() {
        return name;
    }

    public String getHealth() {
        return health;
    }
}
