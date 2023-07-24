package com.example.jpa.domain;

import com.example.jpa.domain.model.Address;
import jakarta.persistence.EntityManager;
import org.hibernate.collection.spi.PersistentBag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberTest {
    @Autowired
    TestEntityManager testEntityManager;
    EntityManager em;

    @BeforeEach
    void init() {
        em = testEntityManager.getEntityManager();
    }

    @Test
    @DisplayName("프록시 테스트")
    void testProxy() {
        /* init */
        Team team = Team.builder()
                .name("team")
                .build();
        testEntityManager.persist(team);

        Member member1 = Member.builder()
                .username("foo")
                .age(10)
                .build();
        member1.setTeam(team);
        testEntityManager.persist(member1);

        for (int i = 0; i < 10; i++) {
            Order order = new Order();
            order.setMember(member1);
        }

        em.flush();
        em.clear();

        /* test */

        Member member2 = em.find(Member.class, member1.getId());
        Team team1 = member2.getTeam();
        List<Order> orders = member2.getOrders();

        System.out.println("team = " + team1.getClass().getName());
        System.out.println("orders = " + orders.getClass().getName());
    }

    @Test
    @DisplayName("값 타입 컬렉션 테스트")
    void testValueCollection() {
        /* init */
        Member member = Member.builder().username("foo").age(10).homeAddress(
                new Address("city", "street", "zipcode")
        ).build();

        // 기본값 타입 컬렉션
        member.getFavoriteFoods().add("치킨");
        member.getFavoriteFoods().add("피자");

        // 임베디드 타입 컬렉션
        member.getAddressHistory().add(new Address("서울", "1", "111-111"));
        member.getAddressHistory().add(new Address("부산", "2", "222-222"));

        testEntityManager.persist(member);

        em.flush();
        em.clear();

        /* test */
        Member member1 = em.find(Member.class, member.getId()); // SQL 실행

        Address homeAddress = member1.getHomeAddress();

        List<String> favoriteFoods = member1.getFavoriteFoods(); // LAZY
        for (String favoriteFood : favoriteFoods) { // SQL 실행
            System.out.println("favoriteFood = " + favoriteFood);
        }

        List<Address> addressHistory = member1.getAddressHistory(); // LAZY
        addressHistory.get(0); // SQL 실행

        System.out.println("favoriteFoods = " + favoriteFoods.getClass().getName());
        System.out.println("addressHistory = " + addressHistory.getClass().getName());
    }

}
