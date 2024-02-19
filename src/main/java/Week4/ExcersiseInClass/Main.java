package Week4.ExcersiseInClass;

import Week3.ExcersiseInClass.Employee;
import Week3.ExcersiseInClass.EmployeeDAO;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

        Teacher teacher = new Teacher("Teacher 1");
        Teacher teacher2 = new Teacher("Teacher 2");
        Course course = new Course("Course 1");
        course.setTeacher(teacher);

       /* try(var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(teacher);
            em.persist(course);
            em.getTransaction().commit();
        }*/

        try(var em = emf.createEntityManager()) {
            Course course1 = em.find(Course.class, 1);
            em.getTransaction().begin();
            course.setTeacher(teacher2);
            em.persist(teacher2);
            em.merge(course);
            em.getTransaction().commit();
            //System.out.println(course1.getTeacher().getName());
        }



        // close connection
        EmployeeDAO.close();
    }
}
