package com.moringaschool;
import com.moringaschool.DAO.AnimalDao;
import com.moringaschool.DAO.EndangeredAnimalDao;
import com.moringaschool.DAO.SightingDao;
import com.moringaschool.Database.DB;
import com.moringaschool.Models.Animal;
import com.moringaschool.Models.EndangeredAnimal;
import com.moringaschool.Models.Sighting;
import org.sql2o.Connection;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

import static spark.SparkBase.port;
import static spark.SparkBase.staticFileLocation;
import org.sql2o.*;

public class Main {
    static Connection con = DB.sql2o.open();
    static AnimalDao animalDao = new AnimalDao(DB.sql2o);
    static EndangeredAnimalDao endangeredAnimalDao = new EndangeredAnimalDao(DB.sql2o);

    static SightingDao sightingDao = new SightingDao(DB.sql2o);
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args) {
        DB.createTables(con);
        port(getHerokuAssignedPort());
        staticFileLocation("/public");
        get("/", (request, response) -> {
            Map<String, List<Animal>> model = new HashMap<>();
            if (animalDao.findAll(con).size() == 0) {
                animalDao.add(new Animal("Rhino"));
                animalDao.add(new Animal("Elephant"));
            }

            model.put("animals", animalDao.findAll(con));

//            System.out.println(animalDao.findAll(con));

            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("endangered", (request, response) -> {
            Map<String, List<EndangeredAnimal>> model = new HashMap<>();
            model.put("endangeredAnimals", endangeredAnimalDao.findAll(con));
            System.out.println(endangeredAnimalDao.findAll(con));
            return new ModelAndView(model, "Endangered.hbs");
        }, new HandlebarsTemplateEngine());

        get("sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("sightings", sightingDao.findAllJoined(con));
            model.put("animals", animalDao.findAll(con));
            return new ModelAndView(model, "Sightings.hbs");
        }, new HandlebarsTemplateEngine());

        get("/addAnimal", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "AddAnimal.hbs");
        }, new HandlebarsTemplateEngine());

        post("/addHandler", (request, response) -> {
            String name = request.queryParams("name");
            String endangered = request.queryParams("endangered");
            System.out.println(endangered);
            Animal newAnimal = new Animal(name);
            animalDao.add(newAnimal);
            if (endangered.equals("endangered")) {
                int age = Integer.parseInt(request.queryParams("age"));
                String health = request.queryParams("health");
                String animal_name = newAnimal.getName();
                int animal_id = newAnimal.getId();
                EndangeredAnimal endangeredAnimal = new EndangeredAnimal(animal_name,animal_id, health, age);
                endangeredAnimalDao.add(endangeredAnimal);
                System.out.println(newAnimal.getId() + " --------------- " + newAnimal.getName());
                System.out.println(age + " ---- " + health);
            }
            response.redirect("/");
            return null;
        });

        get("addSighting", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("animals", animalDao.findAll(con));
            return new ModelAndView(model, "AddSighting.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sightHandler", (request, response) -> {
            int animal = Integer.parseInt(request.queryParams("animal"));
            String location = request.queryParams("location");
            String rangerName = request.queryParams("rangerName");
            sightingDao.add(new Sighting(animal, location, rangerName));
            response.redirect("/sightings");
            return null;
        });

        get("/animal/:id/delete", (request, response) -> {
            int idOfAnimal = Integer.parseInt(request.params("id"));
            animalDao.deleteById(con, idOfAnimal);
            response.redirect("/");
            return null;
        });

        get("/endangeredanimal/:id/delete", (request, response) -> {
            int idOfAnimal = Integer.parseInt(request.params("id"));
            endangeredAnimalDao.deleteById(con, idOfAnimal);
            response.redirect("/endangered");
            return null;
        });

        get("/sighting/:id/delete", (request, response) -> {
            int idOfAnimal = Integer.parseInt(request.params("id"));
            sightingDao.deleteById(con, idOfAnimal);
            response.redirect("/sightings");
            return null;
        });

        get("/reset", (request, response) -> {
            DB.purgeDB(con);
            DB.createTables(con);
            response.redirect("/");
            return null;
        });
    }
}