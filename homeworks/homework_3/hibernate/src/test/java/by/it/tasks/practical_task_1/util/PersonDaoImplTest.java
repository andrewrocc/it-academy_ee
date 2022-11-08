package by.it.tasks.practical_task_1.util;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import by.it.pojos.Person;
import by.it.tasks.practical_task_1.dao.PersonDaoImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

public class PersonDaoImplTest {

    private static EntityManager em;

    PersonDaoImpl targetObject;

    @Before
    public void setUp() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory( "by.it" );
        em = emf.createEntityManager();
        em.getTransaction().begin();
        targetObject = new PersonDaoImpl();

        Person donaldTrump = new Person(null, 100, "Donald", "Trump");
        Person henryFord = new Person(null, 55, "Henry", "Ford");
        Person namelessPerson = new Person(null, 20, "none", "none");
        em.persist(donaldTrump);
        em.persist(henryFord);
        em.persist(namelessPerson);
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
    public void loadExistsObject() {
        //given

        //when
        Person actualPerson = targetObject.load(3L);

        //then
        assertNotNull(actualPerson);
    }

    @Test
    public void getExistsObject() {
        //given

        //when
        Person actualPerson = targetObject.get(3L);

        //then
        assertNotNull(actualPerson);
    }

    @Test
    public void loadNotExistsObject() {
        //given

        //when
        Person actualPerson = targetObject.load(101L);

        //then
        assertNotNull(actualPerson);
    }

    @Test
    public void getNotExistsObject() {
        //given

        //when
        Person actualPerson = targetObject.get(101L);

        //then
        assertNotNull(actualPerson);
    }
}