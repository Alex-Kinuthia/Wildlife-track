// imports
import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

// class sighting and its attributes and access modifiers
public class Sighting {
  private int animal_id;
  private String location;
  private String ranger_name;
  private int id;

// constructor sighting
public Sighting(int animal_id, String location, String ranger_name) {
  this.animal_id = animal_id;
  this.location = location;
  this.ranger_name = ranger_name;
  this.id = id;
}
// to get  id
public int getId() {
  return id;
}
// getting id of the animal
public int getAnimalId() {
  return animal_id;
}
// getting location of the sight
public String getLocation() {
  return location;
}
// ranger name
public String getRangerName() {
  return ranger_name;
}
// list to hold the sighting details and be connected and stored to the database
public static List<Sighting> all() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM sightings;";
    return con.createQuery(sql)
      .throwOnMappingFailure(false)
      .executeAndFetch(Sighting.class);
  }
}
// to enhance the id is found and used within the class
public static Sighting find(int id) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM sightings WHERE id=:id;";
    Sighting sighting = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Sighting.class);
    return sighting;
  } catch (IndexOutOfBoundsException exception) {
    return null;
  }
}

// to override the methods to prevent data redundancy
  @Override
  public boolean equals(Object otherSighting) {
    if(!(otherSighting instanceof Sighting)) {
      return false;
    } else {
      Sighting newSighting = (Sighting) otherSighting;
      return this.getAnimalId() == (newSighting.getAnimalId()) &&
             this.getLocation().equals(newSighting.getLocation()) &&
             this.getRangerName().equals(newSighting.getRangerName());
    }
  }
// save method for storing our sighting data
  public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO sightings (animal_id, location, ranger_name) VALUES (:animal_id, :location, :ranger_name);";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("animal_id", this.animal_id)
      .addParameter("location", this.location)
      .addParameter("ranger_name", this.ranger_name)
      .throwOnMappingFailure(false)
      .executeUpdate()
      .getKey();
  }
}
}
