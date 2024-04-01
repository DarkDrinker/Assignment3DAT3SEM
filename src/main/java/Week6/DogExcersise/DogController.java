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
        updatedDog.setId(id);
        dogs.put(id, updatedDog);
        return updatedDog;
    }

    public static DogDTO deleteDog(int id) {
        return dogs.remove(id);
    }
}
