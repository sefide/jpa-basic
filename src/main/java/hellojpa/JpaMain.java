package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        // Database 연결
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = em.find(Member.class, 7L);
            member.setName("NA");

//            em.detach(member); // update 쿼리 발생 X
            em.clear();

            Member member2 = em.find(Member.class, 7L); // 1차 캐시에 다시 올림 (select)
            System.out.println("======================");
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
