package Week4.Dolphin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Dolphin!");
        DolphinDAO dolphinDAO = new DolphinDAO();

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("dolphin");
        try(EntityManager em = emf.createEntityManager())
        {
            Person p1 = new Person("Hanzi");
            PersonDetail pd1 = new PersonDetail("Algade 3", 4300, "Holbæk", 45);
            p1.addPersonDetail(pd1);
            Person p2 = new Person("Hinterzer");
            PersonDetail pd2 = new PersonDetail("Svanevej", 2400, "Copenhagen", 30);
            Person p3 = new Person("Anne");
            PersonDetail pd3 = new PersonDetail("Soeborgvej", 2400, "Copenhagen", 18);
            Fee f1 = new Fee(125, LocalDate.of(2023, 8, 25));
            Fee f2 = new Fee(150, LocalDate.of(2023, 7, 19));
            Fee f3 = new Fee(3000, LocalDate.of(2001, 4, 22));
            Fee f4 = new Fee(500, LocalDate.of(2008, 12, 1));
            Note n1 = new Note("What is Hinterzer thinking about?");
            Note n2 = new Note("I despise Anne");
            Note n3 = new Note("Im new here");
            p1.addNote(n1);
            p1.addFee(f1);
            p1.addFee(f2);
            p2.addNote(n2);
            p2.addFee(f3);
            p3.addNote(n3);
            p3.addFee(f4);

            em.getTransaction().begin();
                em.persist(p1);
                em.persist(p2);
                em.persist(p3);
            em.getTransaction().commit();


            for (Object fees :
                    dolphinDAO.getallPayments(p1)) {
                System.out.println(fees);
            }

            for (Object note :
                    dolphinDAO.getAllNotesFromPerson(p1)) {
                System.out.println(note.toString());
            }
            dolphinDAO.GetEverythingOnThisPerson(p1);

            dolphinDAO.close();
        }
    }
}