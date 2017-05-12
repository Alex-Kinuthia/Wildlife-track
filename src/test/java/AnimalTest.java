// imports
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

//class Animal test
public class AnimalTest {
  // connectivity with the database
  @Rule
    public DatabaseRule database = new DatabaseRule();

// test to return true if animal's names are the same
    @Test
    public void equals_returnsTrueIfNameIsTheSame_false() {
      Animal firstAnimal = new Animal("Leopard");
      Animal anotherAnimal = new Animal("Leopard");
      assertTrue(firstAnimal.equals(anotherAnimal));
    }
// test to to check if name is correct or not
    @Test
  public void animal_instantiatesCorrectly_false() {
    Animal testAnimal = new Animal("Leopard");
    assertEquals(true, testAnimal instanceof Animal);
  }
// test to get name of the animal
  @Test
  public void getName_animalInstantiatesWithName_Deer() {
    Animal testAnimal = new Animal("Leopard");
    assertEquals("Leopard", testAnimal.getName());
  }
// test to assign id,and saving it into the database
  @Test
public void save_assignsIdToObjectAndSavesObjectToDatabase() {
    Animal testAnimal = new Animal("Leopard");
  testAnimal.save();
  Animal savedAnimal = Animal.all().get(0);
  assertEquals(testAnimal.getId(), savedAnimal.getId());
}
// test to return all instances of animals as true
@Test
public void all_returnsAllInstancesOfAnimal_false() {
  Animal firstAnimal = new Animal("Leopard");
  firstAnimal.save();
  Animal secondAnimal = new Animal("Lion");
  secondAnimal.save();
  assertEquals(true, Animal.all().get(0).equals(firstAnimal));
  assertEquals(true, Animal.all().get(1).equals(secondAnimal));
}
// return animals with the same id
@Test
public void find_returnsAnimalWithSameId_secondAnimal() {
  Animal firstAnimal = new Animal("Leopard");
  firstAnimal.save();
  Animal secondAnimal = new Animal("Lion");
  secondAnimal.save();
  assertEquals(Animal.find(secondAnimal.getId()), secondAnimal);
}
// test to anable one to delete animal
@Test
public void delete_deletesAnimalFromDatabase_0() {
  Animal testAnimal = new Animal("Leopard");
  testAnimal.save();
  testAnimal.delete();
  assertEquals(0, Animal.all().size());
}
// test to enable one to update anial
public void updateName_updatesAnimalNameInDatabase_String() {
  Animal testAnimal = new Animal("Leopard");
  testAnimal.save();
  testAnimal.updateName("Gazelle");
  assertEquals("Gazelle", testAnimal.getName());
}
// test to return nothing when animal is not found
@Test
public void find_returnsNullWhenNoAnimalFound_null() {
  assertTrue(Animal.find(999) == null);
}

}
