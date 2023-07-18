package com.example.jpa.domain;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ParentTest {
    @Autowired
    TestEntityManager testEntityManager;
    EntityManager em;

    @BeforeEach
    void init() {
        em = testEntityManager.getEntityManager();
    }

    @Test
    @DisplayName("Transitive Persistence 테스트")
    void testTransitivePersistence() {
        /* init */
        Parent parent = new Parent();

        Child child1 = new Child();
        Child child2 = new Child();

        child1.setParent(parent);
        child2.setParent(parent);

        em.persist(parent);

//        em.flush();
//        em.clear();

        /* delete */
        System.out.println("===== delete =====");
        em.remove(parent);
    }

    @Test
    @DisplayName("Orphan Removal 테스트")
    @Transactional
    void testOrphanRemoval() {
        /* init */
        Parent parent = new Parent();

        Child child1 = new Child();
        Child child2 = new Child();

        child1.setParent(parent);
        child2.setParent(parent);

        em.persist(parent);

        em.flush();
        em.clear();

        /* delete */
        System.out.println("===== delete2 =====");
        Parent parent1 = em.find(Parent.class, parent.getId());
        System.out.println("removeChildren : " +  parent1.getChildren().remove(0));
    }

    @AfterEach
    void tearDown() {
        Parent parent = em.find(Parent.class, 1);
        System.out.println("check DB : " +  parent.getChildren());

        em.flush();
        em.clear();
    }
}