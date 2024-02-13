package Excersise1And2;

import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        UnicornDAO unicornDAO = new UnicornDAO();

        // Create
        Unicorn UnicornBastian = new Unicorn();
        UnicornBastian.setName("Bastian");
        UnicornBastian.setAge(5);
        UnicornBastian.setPowerStrength(90);
        Unicorn UnicornAnders = new Unicorn();
        UnicornAnders.setName("Anders");
        UnicornAnders.setAge(10);
        UnicornAnders.setPowerStrength(100);
        Unicorn UnicornMads = new Unicorn();
        UnicornMads.setName("Mads");
        UnicornMads.setAge(1);
        UnicornMads.setPowerStrength(10);
        Unicorn newUnicorn = new Unicorn();
        newUnicorn.setName("Sparkle");
        newUnicorn.setAge(5);
        newUnicorn.setPowerStrength(90);
        Unicorn createdUnicorn = unicornDAO.save(UnicornAnders);
        unicornDAO.save(UnicornMads);
        unicornDAO.save(UnicornBastian);
        unicornDAO.save(newUnicorn);

        // Read
        Unicorn foundUnicorn = unicornDAO.findById(createdUnicorn.getId());
        System.out.println("Found Unicorn: " + foundUnicorn.getName());

        // Update
        foundUnicorn.setAge(6);
        Unicorn updatedUnicorn = unicornDAO.update(foundUnicorn);
        System.out.println("Updated Unicorn Age: " + updatedUnicorn.getAge());

        // Delete
        //unicornDAO.delete(createdUnicorn.getId());

        // Bonus - Find all Unicorns in DB and print
        List<Unicorn>listOfUnicorns = unicornDAO.findAll();
        for (Unicorn unicorn :
                listOfUnicorns) {
            System.out.println(unicorn.getName());
            System.out.println(unicorn.getAge());
            System.out.println(unicorn.getPowerStrength());
        }
        unicornDAO.close();
    }
}