package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        // Database 연결
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            Member member = new Member();
//            member.setName("NANA");
//            member.setId(7L);
//
//            System.out.println("BEFORE");
//            em.persist(member);
            Member find = em.find(Member.class, 7L);
            Member find2 = em.find(Member.class, 7L);
            System.out.println("find.id : " + find.getId());
            System.out.println("find.name " + find.getName());
            System.out.println("AFTER");

            tx.commit();
        } catch (Exception e) {
            System.out.println("ERROR");
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
