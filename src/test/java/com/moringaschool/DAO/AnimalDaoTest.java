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
    private AnimalDao animalDao; //ignore me for now. We'll create this soon.
    private Connection conn; //must be sql2o class conn

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "samian", "");
        animalDao = new AnimalDao(sql2o); //ignore me for now
        conn = sql2o.open(); //keep connection open through entire test so it does not get erased
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }
    @Test
    public void addingAnimalId() throws Exception {
        Animal animal = new Animal ("Rhino");
        int originalAnimalId = animal.getId();
        animalDao.add(animal);
        assertNotEquals(originalAnimalId, animal.getId());
    }

    @Test
    public void existingAnimalCanBeFoundById() throws Exception {
        Animal animal = new Animal ("Rhino");
        animalDao.add(animal); //add to dao (takes care of saving)
        Animal foundAnimal = animalDao.findById(conn, animal.getId()); //retrieve
        assertEquals(animal, foundAnimal); //should be the same
    }

    @Test
    void add(){

    }

}