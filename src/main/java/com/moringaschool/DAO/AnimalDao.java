package com.moringaschool.DAO;

import com.moringaschool.Models.Animal;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class AnimalDao implements AnimalDaoInterface{
    private final Sql2o sql2o;

    public AnimalDao(Sql2o sql2o){
        this.sql2o = sql2o; //making the sql2o object available everywhere so we can call methods in it
    }
    @Override
    public void add(Animal animal) {
        String sql = "INSERT INTO animals (id, name) VALUES (:id, :name)";
        try(Connection con = sql2o.open()){
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
    public Animal findById(Connection con, int id) {
        String sql = "SELECT * FROM Animal WHERE id=:id";
        try{
            return con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Animal.class);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void update(Connection con, int id, String name) {
        String sql = "UPDATE Animal SET name=:name WHERE id=:id";
        try{
            con.createQuery(sql).addParameter("name", name).addParameter("id", id).executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Animal> findAll(Connection con) {
        try{
            return con.createQuery("SELECT * FROM Animal")
                    .executeAndFetch(Animal.class);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteById(Connection con, int id) {
        String sql = "DELETE from Animal WHERE id=:id";
        try {
            con.createQuery(sql).addParameter("id", id).executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
