// imports
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

// / class App
public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";
    ProcessBuilder process = new ProcessBuilder();
    Integer port;
    if (process.environment().get("PORT") !=null) {
      port = Integer.parseInt(process.environment().get("PORT"));
    } else {
      port = 4567;
    }

    setPort(port);

 // creating a root route in App.java file that will render our home page

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      // model.put("animals", Animal.all());
      // model.put("endangeredAnimals", EndangeredAnimal.all());
      // model.put("sightings", Sighting.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animal/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("animals", Animal.all());
      // model.put("endangeredAnimals", EndangeredAnimal.all());
      model.put("template", "templates/animal-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animal/:id", (request, response) -> {
  Map<String, Object> model = new HashMap<String, Object>();
  Animal animal = Animal.find(Integer.parseInt(request.params("id")));
  model.put("animal", animal);
  model.put("template", "templates/animal.vtl");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

post("/animal/new", (request, response) -> {
  Map<String, Object> model = new HashMap<String, Object>();
  boolean endangered = request.queryParamsValues("endangered")!=null;
  if (endangered) {
    String name = request.queryParams("name");
    String health = request.queryParams("health");
    String age = request.queryParams("age");
    EndangeredAnimal endangeredAnimal = new EndangeredAnimal(name, health, age);
    endangeredAnimal.save();
    model.put("animals", Animal.all());
    model.put("endangeredAnimals", EndangeredAnimal.all());
  } else {
    String name = request.queryParams("name");
    Animal animal = new Animal(name);
    animal.save();
    model.put("animals", Animal.all());
    model.put("endangeredAnimals", EndangeredAnimal.all());
  }
  response.redirect("/");
    return null;
  });
}
}
