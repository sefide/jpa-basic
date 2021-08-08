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

            Membership membership = new Membership();
            membership.setUsername("MOMO");
            membership.setTeam(team);
            em.persist(membership);

            // 아래 코드를 주석처리하면 findMembership.getTeam().getMembers() 데이터 안나온다.
            team.getMembers().add(membership); // 완전한 객체

//            em.flush();
//            em.clear();

            Membership findMembership = em.find(Membership.class, membership.getId()); // 1차 캐시
            System.out.println("findMembership : " + findMembership.getUsername());
            List<Membership> members = findMembership.getTeam().getMembers();

            System.out.println("=======================");
            for (Membership m : members) {
                System.out.println("membership = " + m.getUsername());
            }
            System.out.println("=======================");

            tx.commit(); // 이 때 insert 진행
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
