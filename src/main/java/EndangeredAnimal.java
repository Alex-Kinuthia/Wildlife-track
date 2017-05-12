// imports
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

// / class EndageredAnimal
public class EndangeredAnimal {
  public String name;
public int id;
public boolean endangered;
private String health;
private String age;

// constructor endangered_animal
public EndangeredAnimal(String name, String health, String age) {
  this.name = name;
  this.id = id;
  this.health = health;
  this.age = age;
}

public String getHealth() {
    return health;
  }

  public String getAge() {
    return age;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

// to return the list of engeredanimals
  public static List<EndangeredAnimal> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM endangered_animals;";
      return con.createQuery(sql)
        .executeAndFetch(EndangeredAnimal.class);
    }
  }
// / to find animals sightings and made static in order to be accessed within the database
  public List<Sighting> getSightings() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM sightings WHERE animal_id=:id;";
      List<Sighting> sightings = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetch(Sighting.class);
    return sightings;
  }
}
// to find animals id and made static in order to be accessed within the class
  public static EndangeredAnimal find(int id) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM endangered_animals WHERE id=:id;";
    EndangeredAnimal endangeredanimal = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(EndangeredAnimal.class);
    return endangeredanimal;
  }
}

  // override method used to prevent data redundancy
  @Override
public boolean equals(Object otherEndangeredAnimal) {
  if(!(otherEndangeredAnimal instanceof EndangeredAnimal)) {
    return false;
  } else {
    EndangeredAnimal newEndangeredAnimal = (EndangeredAnimal) otherEndangeredAnimal;
    return this.getName().equals(newEndangeredAnimal.getName()) && this.getHealth().equals(newEndangeredAnimal.getHealth()) && this.getAge().equals(newEndangeredAnimal.getAge());
  }
}

//method for saving animals in the database
public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO endangered_animals (name, health, age) VALUES (:name, :health, :age);";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("health", this.health)
      .addParameter("age", this.age)
      .executeUpdate()
      .getKey();
  }
}
//method for updating health of endangeredAnimals in the database
public void updateHealth(String health) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "UPDATE endangered_animals SET health=:health WHERE id=:id;";
    con.createQuery(sql)
      .addParameter("id", id)
      .addParameter("health", health)
      .executeUpdate();
  }
}
// /method for updating age of endangeredAnimals in the database
public void updateAge(String age) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "UPDATE endangered_animals SET age=:age WHERE id=:id;";
    con.createQuery(sql)
      .addParameter("age", age)
      .addParameter("id", id)
      .executeUpdate();
  }
}
}
