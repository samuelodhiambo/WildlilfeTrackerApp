package com.moringaschool.DAO;

import com.moringaschool.Database.DB;
import com.moringaschool.Models.EndangeredAnimal;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;

import static org.junit.jupiter.api.Assertions.*;

class EndangeredAnimalDaoTest {
    private Connection con = DB.sql2o.open();
    private EndangeredAnimalDao endangeredAnimalDao = new EndangeredAnimalDao(DB.sql2o);

    @Test
    void add() {
        EndangeredAnimal enAnimal = new EndangeredAnimal("Rhino",1, "sickly", 29);
        endangeredAnimalDao.add(enAnimal);

        assertEquals(enAnimal.getId(), endangeredAnimalDao.findById(enAnimal.getId()).getId());
    }
}