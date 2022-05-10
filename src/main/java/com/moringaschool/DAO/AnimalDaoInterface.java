package com.moringaschool.DAO;

import com.moringaschool.Models.Animal;
import org.sql2o.Connection;

import java.util.List;

public interface AnimalDaoInterface {
    void add(Animal animal);

    Animal findById(int id);

    void update(Connection con, int id, String name);

    List<Animal> findAll(Connection con);

    void deleteById(Connection con, int id);
}
