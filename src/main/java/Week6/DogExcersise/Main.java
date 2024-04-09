package Week6.DogExcersise;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {
    public static void main(String[] args) {
        DogController.populateDogs();
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

    private static void getAllDogs(Context ctx) {
        ctx.json(DogController.getAllDogs());
    }

    private static void getDogById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        DogDTO dog = DogController.getDogById(id);
        if (dog != null) {
            ctx.json(dog);
        } else {
            throw new NotFoundResponse("Dog not found");
        }
    }

    private static void createDog(Context ctx) {
        DogDTO dog = ctx.bodyAsClass(DogDTO.class);
        try {
            DogController.createDog(dog);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ctx.json(dog);
    }

    private static void updateDog(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        DogController.updateDog(id, ctx.bodyAsClass(DogDTO.class));
        ctx.json(DogController.getDogById(id));
    }

    private static void deleteDog(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        DogDTO Dog = DogController.getDogById(id);
        if (Dog == null) {
            throw new NotFoundResponse("Dog not found");
        } else {
            DogController.deleteDog(id);
        }
    }

}