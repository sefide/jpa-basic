package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class JpaMain {

    public static void main(String[] args) {
        // Database 연결
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
           Member member = new Member();
           member.setUsername("MOMO");
           member.setCreatedBy("Cream");
           member.setCreatedAt(LocalDateTime.now());

           em.persist(member);
           em.flush();
           em.clear();

            tx.commit();
            System.out.println("COMMIT");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("ERROR");
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
