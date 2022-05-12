package com.moringaschool.DAO;

import com.moringaschool.Database.DB;
import com.moringaschool.Models.Animal;
import com.moringaschool.Models.Sighting;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class SightingDao implements SightingsDaoInterface{
    private final Sql2o sql2o;

    public SightingDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Sighting sight) {
        String sql = "INSERT INTO sightings (animal_id, location, rangerName, time) VALUES (:animal_id, :location, :rangerName, now())";
        try(Connection con = DB.sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(sight)
                    .executeUpdate()
                    .getKey();
        } catch (Sql2oException ex) {
            System.out.println("there was a problem adding the animal " + ex);
        }
    }

    @Override
    public Sighting findById(int animal_id) {
        String sql = "SELECT * FROM sightings WHERE animal_id=:animal_id";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql).addParameter("animal_id", animal_id).executeAndFetchFirst(Sighting.class);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void update(Connection con, int animal_id, String location) {
        String sql = "UPDATE sightings SET location=:location WHERE animal_id=:animal_id";
        try{
            con.createQuery(sql).addParameter("location", location).addParameter("animal_id", animal_id).executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Sighting> findAll(Connection con) {
        try{
            return con.createQuery("SELECT * FROM sightings")
                    .executeAndFetch(Sighting.class);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteById(Connection con, int animal_id) {
        String sql = "DELETE from sightings WHERE animal_id=:animal_id";
        try {
            con.createQuery(sql).addParameter("animal_id", animal_id).executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
