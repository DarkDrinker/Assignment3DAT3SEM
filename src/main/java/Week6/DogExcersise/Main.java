package Week6.DogExcersise;

import lombok.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {

     static List<Dog> dogs = Arrays.asList(
             new Dog(1,"Hugo", "German Shepard", "Female", 10)
     );


    public static void main(String[] args) {

//        Javalin app = Javalin.create().start(7007);
        ApplicationConfig applicationConfig = ApplicationConfig.getInstance();
        applicationConfig.initiateServer()
                .startServer(7007)
                .setExceptionHandling()
                .setRoute( () -> {
            path("dogs", () -> {
                //find all dogs
                get("/", ctx -> {
                    ctx.json(dogs);
                });
                //find by id
                get("dog/{id}", ctx -> {

                });
                //insert dog
                post("dog", ctx -> {

                });
                put("dog/{id}", ctx -> {

                });
                delete("dog/{id}", ctx -> {

                });
            });
        });
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @EqualsAndHashCode
    private static class Dog {
        int id;
        String name;
        String breed;
        String gender;
        int age;
    }
}