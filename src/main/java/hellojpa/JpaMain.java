package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        // Database 연결
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("AAA");
            em.persist(team);

            Membership user = new Membership();
            user.setUsername("MOMO");
            user.setTeam(team);
            em.persist(user);

            em.flush();
            em.clear();

            Membership findMembership = em.find(Membership.class, user.getId());
            System.out.println("findMembership : " + findMembership.getUsername());
            List<Membership> members = findMembership.getTeam().getMembers();

            for (Membership m : members) {
                System.out.println("membership = " + m.getUsername());
            }

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

    public void pkMethod(EntityManager em) {
        Member member = new Member();
        member.setId(2L);
        member.setUsername("A");
        member.setRoleType(RoleType.USER);
        member.setCreatedDate(new Date());
        member.setTestDateTime(LocalDateTime.now());

        em.persist(member);

        Item item = new Item();
        item.setName("title");

        em.persist(item);
    }
}
