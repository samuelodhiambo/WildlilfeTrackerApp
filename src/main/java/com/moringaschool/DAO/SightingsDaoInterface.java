package com.moringaschool.DAO;

import com.moringaschool.Models.EndangeredAnimal;
import com.moringaschool.Models.Sighting;
import org.sql2o.Connection;

import java.util.List;

public interface SightingsDaoInterface {
    void add(Sighting sight);

    Sighting findById(int id);

    void update(Connection con, int id, String name);

    List<Sighting> findAll(Connection con);

    void deleteById(Connection con, int id);
}
