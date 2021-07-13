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
            /*
            // 저장
            Member member = new Member();
            member.setId(1L);
            member.setName("MOMO");

            em.persist(member);
             */

            /*
            // 조회
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.id : " + findMember.getId());
            System.out.println("findMember.name : " + findMember.getName());
             */

            // 수장
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("DD");

            tx.commit();
        } catch (Exception e) {
            System.out.println("실패 " + e.getMessage());
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
