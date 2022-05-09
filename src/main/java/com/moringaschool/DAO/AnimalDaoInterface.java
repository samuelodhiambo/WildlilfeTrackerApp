package com.moringaschool.DAO;

import com.moringaschool.Models.Animal;
import org.sql2o.Connection;

import java.util.List;

public interface AnimalDaoInterface {
    void add(Connection con, Animal animal);

    List<Animal> findAll(Connection con);
}
