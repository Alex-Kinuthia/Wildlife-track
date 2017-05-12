// imports
import org.junit.*;
import org.sql2o.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.text.DateFormat;
import java.util.Date;

// class sighting test
public class SightingTest {

// connectivity with the database
  @Rule
  public DatabaseRule database = new DatabaseRule();

// / test to return true if sighting descriptions are the same
  @Test
  public void equals_returnsTrueIfLocationAndDescriptionAreSame_true() {
    Animal testAnimal = new Animal("Leopard");
    testAnimal.save();
    Sighting testSighting = new Sighting(testAnimal.getId(), "6.0, 2.0", "Ranger Aleki");
    Sighting anotherSighting = new Sighting(testAnimal.getId(), "2.8, 1.0", "Ranger Remy");
    assertTrue(testSighting.equals(anotherSighting));
  }

@Test
public void sighting_instantiatesCorrectly_true() {
  Animal testAnimal = new Animal("Leopard");
  testAnimal.save();
  Sighting testSighting = new Sighting(testAnimal.getId(), "6.0, 2.0", "Ranger Aleki");
  assertEquals(true, testSighting instanceof Sighting);
}

// test to retrieve all animals into the database
@Test
public void save_insertsObjectIntoDatabase_Sighting() {
  Animal testAnimal = new Animal("Leopard");
  testAnimal.save();
  Sighting testSighting = new Sighting (testAnimal.getId(), "6.0, 2.0", "Ranger Aleki");
  testSighting.save();
  assertEquals(true, Sighting.all().get(0).equals(testSighting));
}

// test to return all instances of animals as true
@Test
public void all_returnsAllInstancesOfSighting_true() {
  Animal testAnimal = new Animal("Leopard");
  testAnimal.save();
  Sighting testSighting = new Sighting (testAnimal.getId(), "6.0, 2.0", "Ranger Aleki");
  testSighting.save();
  Animal secondTestAnimal = new Animal("Gazelle");
  secondTestAnimal.save();
  Sighting secondTestSighting = new Sighting (testAnimal.getId(), "2.8, 1.0", "Ranger Remy");
  secondTestSighting.save();
  assertEquals(true, Sighting.all().get(0).equals(testSighting));
  assertEquals(true, Sighting.all().get(1).equals(secondTestSighting));
}

// test to return all instances of animals as true
@Test
public void find_returnsSightingWithSameId_secondSighting() {
  Animal testAnimal = new Animal("Leopard");
  testAnimal.save();
  Sighting testSighting = new Sighting (testAnimal.getId(), "6.0, 2.0", "Ranger Aleki");
  testSighting.save();
  Animal secondTestAnimal = new Animal("Gazelle");
  secondTestAnimal.save();
  Sighting secondTestSighting = new Sighting (testAnimal.getId(), "2.8, 1.0", "Ranger Remy");
  secondTestSighting.save();
  assertEquals(Sighting.find(secondTestSighting.getId()), secondTestSighting);
}

@Test
public void find_returnsNullWhenNoAnimalFound_null() {
  assertTrue(Animal.find(999) == null);
}

}
