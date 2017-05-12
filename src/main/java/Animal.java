// imports
import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

// class animal
public class Animal {
  public String name;
  public int id;

// constructor animal
public Animal(String name) {
  this.name = name;
  this.id = id;
}
// used to return the details of the animal
  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }
// to return the list of animals
  public static List<Animal> all() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM animals;";
    return con.createQuery(sql)
      .executeAndFetch(Animal.class);
  }
}

// to find animals id and made static in order to be accessed within the class
public static Animal find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals WHERE id=:id;";
      Animal animal = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Animal.class);
      return animal;
    }
  }

  // override method used to prevent data redundancy
  @Override
  public boolean equals(Object otherAnimal) {
    if(!(otherAnimal instanceof Animal)) {
      return false;
    } else {
      Animal newAnimal = (Animal) otherAnimal;
      return this.getName().equals(newAnimal.getName());
    }
  }
//method for saving animals in the database
  public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO animals (name) VALUES (:name);";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .executeUpdate()
      .getKey();
  }
}
//method for updating name of animals in the database
public void updateName(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE animals SET name=:name WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .addParameter("name", name)
        .executeUpdate();
    }
  }
//method for deleting animals in the database
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM animals WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
// to find animals sightings and made static in order to be accessed within the database
  public List<Sighting> getSightings() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM sightings WHERE animal_id=:id;";
      List<Sighting> sightings = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetch(Sighting.class);
    return sightings;
  }
}
}
