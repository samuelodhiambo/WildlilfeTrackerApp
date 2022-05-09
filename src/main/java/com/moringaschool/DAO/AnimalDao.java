package com.moringaschool.DAO;

import com.moringaschool.Models.Animal;

import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;

public class AnimalDao implements AnimalDaoInterface{
    @Override
    public void add(Connection con, Animal animal) {
        String sql = "INSERT INTO animals (id, name) VALUES (:id, :name)";
        try{
            int id = (int) con.createQuery(sql, true)
                    .bind(animal) //use name and location from match object for sql
                    .executeUpdate()
                    .getKey();
            animal.setId(id);
        } catch (Sql2oException ex) {
            System.out.println("there was a problem adding the animal");
        }
    }

    @Override
    public List<Animal> findAll(Connection con) {
        try{
            return con.createQuery("SELECT * FROM matches")
                    .executeAndFetch(Animal.class);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
