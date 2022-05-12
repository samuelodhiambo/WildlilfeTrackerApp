package com.moringaschool.DAO;

import com.moringaschool.Models.EndangeredAnimal;
import org.sql2o.Connection;

import java.util.List;

public interface EndangeredAnimalInterface {
    void add(EndangeredAnimal animal);

    EndangeredAnimal findById(int id);

    void update(Connection con, int id, String name);

    List<EndangeredAnimal> findAll(Connection con);

    void deleteById(Connection con, int id);
}
