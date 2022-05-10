package com.moringaschool;
import com.moringaschool.DAO.AnimalDao;
import com.moringaschool.Models.Animal;
import org.sql2o.Connection;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

import static spark.SparkBase.port;
import static spark.SparkBase.staticFileLocation;

public class Main {
    static Connection con;
    static AnimalDao animalDao;
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();

//            animalDao.add(new Animal("Rhino"));
//            animalDao.add(new Animal("Elephant"));

            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("endangered", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "Endangered.hbs");
        }, new HandlebarsTemplateEngine());

        get("sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "Sightings.hbs");
        }, new HandlebarsTemplateEngine());

        get("addAnimal", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "AddAnimal.hbs");
        }, new HandlebarsTemplateEngine());

        get("addSighting", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "AddSighting.hbs");
        }, new HandlebarsTemplateEngine());
    }
}