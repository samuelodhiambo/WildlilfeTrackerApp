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
//        System.out.println(endangeredAnimal.getAnimal_name() + ", " + endangeredAnimal.getId() + ", " + endangeredAnimal.getHealth() + ", " + endangeredAnimal.getAge() + ", " + endangeredAnimal.getAnimal_id());
        String sql = "INSERT INTO endangeredanimals (\"name\", animal_id, health, \"age\") VALUES (:animal_name, :animal_id, :health, :age)";
        sql = "insert into endangeredanimals (\n" +
                "    \"age\",\n" +
                "    animal_id,\n" +
                "    health,\n" +
                "    \"name\")\n" +
                "values (\n" +
                "    :age,\n" +
                "    :animal_id,\n" +
                "    :health,\n" +
                "    :animal_name)\n" +
                ";\n";
        try(Connection con = DB.sql2o.open()){
//            System.out.println(con.createQuery(sql).bind(endangeredAnimal).getColumnMappings());
            int id = (int) con.createQuery(sql, true)
                    .bind(endangeredAnimal)
                    .executeUpdate()
                    .getKey();
            endangeredAnimal.setId(id);
        } catch (Sql2oException ex) {
            System.out.println("there was a problem adding the animal " + ex.getCause());
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
            return con.createQuery("select id, name as animal_name, animal_id, health, age from endangeredanimals")
                    .executeAndFetch(EndangeredAnimal.class);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteById(Connection con, int id) {
        try{
            con.createQuery("DELETE FROM endangeredanimals WHERE id=:id").addParameter("id", id).executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException();
        }
    }
}
