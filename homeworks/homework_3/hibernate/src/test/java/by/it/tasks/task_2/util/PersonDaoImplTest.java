package by.it.tasks.task_2.util;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import by.it.pojos.Person;
import lombok.SneakyThrows;
import by.it.tasks.task_2.dao.PersonDaoImpl;
import by.it.tasks.task_3.dao.PersonDaoSynchronizeImpl;

import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.Assert.assertEquals;

public class PersonDaoImplTest {

    private static EntityManager em;

    PersonDaoImpl targetObject;

    @Before
    public void setUp() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory( "by.it" );
        em = emf.createEntityManager();
        em.getTransaction().begin();
        targetObject = new PersonDaoImpl();
        PersonDaoSynchronizeImpl entityManagerDaoSynchronize = new PersonDaoSynchronizeImpl(em);

        Person donaldTrump = new Person(null, 100, "Donald", "Trump");
        Person henryFord = new Person(null, 55, "Henry", "Ford");
        Person namelessPerson = new Person(null, 20, "none", "none");
        em.persist(donaldTrump);
        em.persist(henryFord);
        em.persist(namelessPerson);
        entityManagerDaoSynchronize.flushAndClearEntityManager();
        em.getTransaction().commit();
    }

    @After
    public void tearDown() throws Exception {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        em.createQuery("DELETE FROM Person").executeUpdate();
        em.getTransaction().commit();
        em.getEntityManagerFactory().close();
        em.close();
        targetObject = null;
    }

    @Test
    @SneakyThrows
    public void create() {
        //given
        long queryResult = (Long) em.createQuery("SELECT count(*) FROM Person").getSingleResult();
        assertEquals(3L, queryResult);

        //when
        Person johnDoe = new Person(null, 30, "John", "Doe");
        targetObject.create(johnDoe);

        //then
        long actualSize = (Long) em.createQuery("SELECT count(*) FROM Person").getSingleResult();
        assertEquals(4L, actualSize);
    }

    @Test
    public void findById() {
        //given

        //when
        Person p = targetObject.findById(1L);

        //then
        assertEquals(100, p.getAge());
    }

    @Test
    public void update() {
        create();
    }

    @Test
    public void delete() {
        //given
//        Person namelessPerson = new Person(3L, 20, "none", "none"); or string below
        Person p = targetObject.findById(3L);
        long queryResult = (Long) em.createQuery("SELECT count(*) FROM Person").getSingleResult();
        assertEquals(3L, queryResult);

        //when
        targetObject.delete(p);

        //then
        long actualSize = (Long) em.createQuery("SELECT count(*) FROM Person").getSingleResult();
        assertEquals(2L, actualSize);
    }
}