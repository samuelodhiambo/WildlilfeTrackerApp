package com.moringaschool.Database;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DB {
//    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/samian", "samian" ,"root");

    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://ec2-3-231-82-226.compute-1.amazonaws.com:5432/d7gfsmtekc762e", "mitfkzzkyneohn" ,"91b27d14f8ed0446475790764b5349a9b93270c12e2df00eb9fdb2b41ff6714b");

    public static void createTables(Connection con) {
        try {
            con.createQuery("CREATE TABLE IF NOT EXISTS animals (id SERIAL PRIMARY KEY, name VARCHAR UNIQUE) ").executeUpdate();
        } catch (Exception err) {
            throw new RuntimeException(err);
        }

        try {
            con.createQuery("CREATE TABLE IF NOT EXISTS endangeredanimals (id SERIAL PRIMARY KEY, name VARCHAR UNIQUE, animal_id INT, health VARCHAR, age INT , CONSTRAINT fk_animal FOREIGN KEY(animal_id) REFERENCES animals(id) ON DELETE CASCADE) ").executeUpdate();
        } catch (Exception err) {
            throw new RuntimeException(err);
        }

        try {
            con.createQuery("CREATE TABLE IF NOT EXISTS sightings (animal_id INT, location VARCHAR, rangerName VARCHAR, time TIMESTAMP, CONSTRAINT fk_animal FOREIGN KEY(animal_id) REFERENCES animals(id) ON DELETE CASCADE) ").executeUpdate();
        } catch (Exception err) {
            throw new RuntimeException(err);
        }
    }

    public static void purgeDB(Connection con) {
        try {
            con.createQuery("DROP TABLE sightings").executeUpdate();
        } catch (Exception err) {
            throw new RuntimeException(err);
        }

        try {
            con.createQuery("DROP TABLE endangeredanimals").executeUpdate();
        } catch (Exception err) {
            throw new RuntimeException(err);
        }

        try {
            con.createQuery("DROP TABLE animals").executeUpdate();
        } catch (Exception err) {
            throw new RuntimeException(err);
        }
    }
}
