package Week6.DogExcersise;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.util.HashMap;
import java.util.Map;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {
    private static final Map<Integer, DogDTO> dogs = new HashMap<>();
    private static int nextId = 1;



    public static void main(String[] args) {
        populateDogs();
//        Javalin app = Javalin.create().start(7007);
        ApplicationConfig applicationConfig = ApplicationConfig.getInstance();
        applicationConfig.initiateServer()
                .startServer(7007)
                .setExceptionHandling()
                .setRoute( () -> {
            path("dogs", () -> {
                //find all dogs
                get("/", Main::getAllDogs);
                //find by id
                get("dog/{id}", Main::getDogById);
                //insert dog
                post("dog", Main::createDog);
                //update dog
                put("dog/{id}", Main::updateDog);
                //delete dog
                delete("dog/{id}", Main::deleteDog);

            });

        });

    }
    private static void populateDogs() {
        // Sample dogs
        DogDTO dog1 = new DogDTO(nextId++, "Buddy", "Golden Retriever", "Male", 3);
        DogDTO dog2 = new DogDTO(nextId++, "Bailey", "Labrador Retriever", "Female", 2);
        DogDTO dog3 = new DogDTO(nextId++, "Max", "German Shepherd", "Male", 4);

        // Add dogs to the map
        dogs.put(dog1.getId(), dog1);
        dogs.put(dog2.getId(), dog2);
        dogs.put(dog3.getId(), dog3);
    }
    private static void getAllDogs(Context ctx) {
        ctx.json(dogs.values());
    }

    private static void getDogById(Context ctx) {
        int id = ctx.pathParam("id", Integer.class).get();
        DogDTO dog = dogs.get(id);
        if (dog != null) {
            ctx.json(dog);
        } else {
            throw new NotFoundResponse("Dog not found");
        }
    }

    private static void createDog(Context ctx) {
        DogDTO dog = ctx.bodyAsClass(DogDTO.class);
        dog.setId(nextId++);
        dogs.put(dog.getId(), dog);
        ctx.json(dog);
    }

    private static void updateDog(Context ctx) {
        int id = (String) ctx.pathParam("id", Integer.class).get();
        DogDTO existingDog = dogs.get(id);
        if (existingDog != null) {
            DogDTO updatedDog = ctx.bodyAsClass(DogDTO.class);
            updatedDog.setId(id);
            dogs.put(id, updatedDog);
            ctx.json(updatedDog);
        } else {
            throw new NotFoundResponse("Dog not found");
        }
    }

    private static void deleteDog(Context ctx) {
        int id = ctx.pathParam("id", Integer.class).get();
        DogDTO removedDog = dogs.remove(id);
        if (removedDog != null) {
            ctx.json(removedDog);
        } else {
            throw new NotFoundResponse("Dog not found");
        }
    }

}