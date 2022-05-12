package com.moringaschool.Models;

import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;

public class Sighting {
    private int animal_id;
    private String location;
    private String rangerName;

    private Date time;

    public Sighting(int animal_id, String location, String rangerName) {
        this.animal_id = animal_id;
        this.location = location;
        this.rangerName = rangerName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sighting)) return false;
        Sighting animal = (Sighting) o;
        return getLocation() == animal.getLocation() &&
                Objects.equals(getLocation(), animal.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLocation(), getLocation());
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
}
