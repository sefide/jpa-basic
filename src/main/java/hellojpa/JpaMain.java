package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.Date;

public class JpaMain {

    public static void main(String[] args) {
        // Database 연결
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setId(2L);
            member.setUsername("A");
            member.setRoleType(RoleType.USER);
            member.setCreatedDate(new Date());
            member.setTestDateTime(LocalDateTime.now());

            em.persist(member);

            Item item = new Item();
//            item.setId("ID_ASDWE");
            item.setName("title");

            em.persist(item);
            tx.commit();
            System.out.println("COMMIT");
        } catch (Exception e) {
            System.out.println("ERROR");
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
