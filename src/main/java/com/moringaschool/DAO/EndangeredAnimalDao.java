package com.moringaschool.DAO;

import com.moringaschool.Database.DB;
import com.moringaschool.Models.EndangeredAnimal;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class EndangeredAnimalDao implements EndangeredAnimalInterface{
    private final Sql2o sql2o;

    public EndangeredAnimalDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(EndangeredAnimal endangeredAnimal) {
        String sql = "INSERT INTO endangeredanimals (name, animal_id, health, age) VALUES (:name, :animal_id, :health, :age)";
        try(Connection con = DB.sql2o.open()){
            System.out.println(con.createQuery(sql));
            int id = (int) con.createQuery(sql, true)
                    .bind(endangeredAnimal)
                    .executeUpdate()
                    .getKey();
            endangeredAnimal.setId(id);
        } catch (Sql2oException ex) {
            System.out.println("there was a problem adding the animal " + ex);
        }
    }

    @Override
    public EndangeredAnimal findById(int id) {
        return null;
    }

    @Override
    public void update(Connection con, int id, String name) {

    }

    @Override
    public List<EndangeredAnimal> findAll(Connection con) {
        try{
            return con.createQuery("SELECT * FROM endangeredanimals")
                    .executeAndFetch(EndangeredAnimal.class);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteById(Connection con, int id) {

    }
}
