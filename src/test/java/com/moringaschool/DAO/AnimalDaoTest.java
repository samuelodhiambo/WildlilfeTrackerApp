package com.moringaschool.DAO;

import com.moringaschool.Database.DB;
import com.moringaschool.Models.Animal;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.*;

import static org.junit.jupiter.api.Assertions.*;

class AnimalDaoTest {
    private AnimalDao animalDao = new AnimalDao(DB.sql2o);
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/samian";
        Sql2o sql2o = new Sql2o(connectionString, "samian", "root");
        animalDao = new AnimalDao(sql2o); //ignore me for now
        conn = sql2o.open(); //keep connection open through entire test so it does not get erased
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }
    @Test
    public void addingAnimalId() throws Exception {
        Animal animal = new Animal ("Kangaroo");
        int originalAnimalId = animal.getId();
        animalDao.add(animal);
        assertNotEquals(originalAnimalId, animal.getId());
    }

    @Test
    public void existingAnimalCanBeFoundById() throws Exception {
        Animal animal = new Animal ("Hyena");
        animalDao.add(animal); //add to dao (takes care of saving)
        Animal foundAnimal = animalDao.findById(animal.getId()); //retrieve
        assertEquals(animal, foundAnimal); //should be the same
    }

    @Test
    void add(){

    }

}