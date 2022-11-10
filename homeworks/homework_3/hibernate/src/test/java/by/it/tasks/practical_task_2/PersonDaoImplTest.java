package by.it.tasks.practical_task_2;

import org.junit.*;
import by.it.pojos.Person;
import by.it.tasks.task_3.dao.PersonDaoSynchronizeImpl;
import org.junit.runners.MethodSorters;

import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonDaoImplTest {

    private static EntityManager em;

    private static PersonDaoSynchronizeImpl entityManagerDaoSynchronize;

    static PersonDaoImpl targetObject;

    @BeforeClass
    public static void setUp() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("by.it");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        targetObject = new PersonDaoImpl();
        entityManagerDaoSynchronize = new PersonDaoSynchronizeImpl(em);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        em.createQuery("DELETE FROM Person").executeUpdate();
        entityManagerDaoSynchronize.synchronizeEntityManager();
        em.getEntityManagerFactory().close();
        em.close();
        targetObject = null;
    }

    @Test
    public void createEntity() {
        //given
        long queryResult = (Long) em.createQuery("SELECT count(*) FROM Person").getSingleResult();
        assertEquals(0L, queryResult);

        Person p = new Person();
        p.setAge(12);
        p.setName("Toto");
        p.setSurname("Wolf");

        //when
        boolean createResult = targetObject.createEntity(Person.class.getName(), p);

        //then
        entityManagerDaoSynchronize.synchronizeEntityManager();
        long actualSize = (Long) em.createQuery("SELECT count(*) FROM Person").getSingleResult();
        assertEquals(1L, actualSize);
        assertTrue(createResult);
    }

    @Test
    public void createEntityVoid() {
        //given
        long queryResult = (Long) em.createQuery("SELECT count(*) FROM Person").getSingleResult();
        assertEquals(1L, queryResult);

        Person p = new Person();
        p.setAge(20);
        p.setName("Christian");
        p.setSurname("Horner");

        //when
        targetObject.createEntity(p);

        //then
        entityManagerDaoSynchronize.synchronizeEntityManager();
        long actualSize = (Long) em.createQuery("SELECT count(*) FROM Person").getSingleResult();
        assertEquals(2L, actualSize);
    }

    @Test
    public void deleteEntity() {
        //given
        long queryResult = (Long) em.createQuery("SELECT count(*) FROM Person").getSingleResult();
        assertEquals(2L, queryResult);

        Person p = new Person();
        p.setId(1L);
        p.setAge(12);
        p.setName("Toto");
        p.setSurname("Wolf");

        //when
        boolean deleteResult = targetObject.deleteEntity(Person.class.getName(), p);

        //then
        entityManagerDaoSynchronize.synchronizeEntityManager();
        long actualSize = (Long) em.createQuery("SELECT count(*) FROM Person").getSingleResult();
        assertEquals(1L, actualSize);
        assertTrue(deleteResult);
    }

    @Test
    public void deleteEntityVoid() {
        //given
        long queryResult = (Long) em.createQuery("SELECT count(*) FROM Person").getSingleResult();
        assertEquals(1L, queryResult);

        Person p = new Person();
        p.setId(2L);
        p.setAge(20);
        p.setName("Christian");
        p.setSurname("Horner");

        //when
        targetObject.deleteEntity(Person.class.getName(), p);

        //then
        entityManagerDaoSynchronize.synchronizeEntityManager();
        long actualSize = (Long) em.createQuery("SELECT count(*) FROM Person").getSingleResult();
        assertEquals(0L, actualSize);
    }
}