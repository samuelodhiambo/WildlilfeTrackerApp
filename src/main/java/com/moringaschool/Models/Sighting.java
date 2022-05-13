package com.moringaschool.Models;

import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;

public class Sighting {
    private int animal_id;
    private String location;
    private String rangerName;

    private String name;

    private String id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sighting)) return false;
        Sighting sighting = (Sighting) o;
        return getAnimal_id() == sighting.getAnimal_id() && getLocation().equals(sighting.getLocation()) && getRangerName().equals(sighting.getRangerName()) && getName().equals(sighting.getName()) && getId().equals(sighting.getId()) && getTime().equals(sighting.getTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAnimal_id(), getLocation(), getRangerName(), getName(), getId(), getTime());
    }

    private Date time;

    public Sighting(int animal_id, String location, String rangerName) {
        this.animal_id = animal_id;
        this.location = location;
        this.rangerName = rangerName;
    }


    public int getAnimal_id() {
        return animal_id;
    }

    public String getLocation() {
        return location;
    }

    public String getRangerName() {
        return rangerName;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTime() {
        return DateFormat.getDateTimeInstance().format(time);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
