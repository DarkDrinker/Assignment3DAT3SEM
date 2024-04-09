package Week6.DogExcersise;

import Week6.DogExcersise.DogDTO;

import java.util.HashMap;
import java.util.Map;

public class DogController {

    private static final Map<Integer, DogDTO> dogs = new HashMap<>();
    private static int nextId = 1;

    public static Map<Integer, DogDTO> getAllDogs() {
        return dogs;
    }

    public static DogDTO getDogById(int id) {
        return dogs.get(id);
    }

    public static DogDTO createDog(DogDTO dog) {
        dog.setId(nextId++);
        dogs.put(dog.getId(), dog);
        return dog;
    }

    public static DogDTO updateDog(int id, DogDTO updatedDog) {
        dogs.put(id, updatedDog);
        return updatedDog;
    }

    public static DogDTO deleteDog(int id) {
        return dogs.remove(id);
    }

    public static void populateDogs() {
        // Sample dogs
        DogDTO dog1 = new DogDTO(nextId++, "Buddy", "Golden Retriever", "Male", 3);
        DogDTO dog2 = new DogDTO(nextId++, "Bailey", "Labrador Retriever", "Female", 2);
        DogDTO dog3 = new DogDTO(nextId++, "Max", "German Shepherd", "Male", 4);

        // Add dogs to the map

        dogs.put(dog1.getId(), dog1);
        dogs.put(dog2.getId(), dog2);
        dogs.put(dog3.getId(), dog3);
    }
}
