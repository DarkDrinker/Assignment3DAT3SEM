package Week3.Excersise3;

import Week3.ExcersiseInClass.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class StudentDAO {

    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public static void createStudent(Student student){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
        em.close();
    }

    public static Student readStudent(int id){
        EntityManager em = emf.createEntityManager();
        Student foundStudent = em.find(Student.class, id);
        em.close();
        return foundStudent;
    }

    public static Student updateStudent(Student updStd){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Student updatedStudent = em.merge(updStd);
        em.getTransaction().commit();
        em.close();
        return updatedStudent;
    }

    public static void deleteStudent(int id){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Student student = readStudent(id);
        if (student != null)
        {
            em.remove(student);
        }
        em.getTransaction().commit();
        em.close();
    }

    public static List<Student> readAllStudents(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Student> query = em.createQuery("SELECT u FROM Student u", Student.class);
        return query.getResultList();
    }
}
