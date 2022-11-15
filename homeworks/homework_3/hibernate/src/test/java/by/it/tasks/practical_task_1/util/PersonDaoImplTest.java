package by.it.tasks.practical_task_1.util;

import by.it.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.*;
import by.it.pojos.Person;
import by.it.tasks.practical_task_1.dao.PersonDaoImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

//@Ignore
public class PersonDaoImplTest {

    private static Session session;

    private static EntityManager em;

    PersonDaoImpl targetObject;

    @Before
    public  void setUp() throws Exception {
        session = HibernateUtil.getSession();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("by.it");
        em = emf.createEntityManager();
        targetObject = new PersonDaoImpl(session);

        fillDataToDB();
    }

    public static void fillDataToDB() {
        Person donaldTrump = new Person(null, 100, "Donald", "Trump");
        Person henryFord = new Person(null, 55, "Henry", "Ford");
        Person namelessPerson = new Person(null, 20, "none", "none");
        session.getTransaction().begin();
        session.persist(donaldTrump);
        session.persist(henryFord);
        session.persist(namelessPerson);
        session.getTransaction().commit();
    }

    @After
    public void tearDown() throws Exception {
        if (!session.getTransaction().isActive() && em.isOpen()) {
//            em.getEntityManagerFactory().close();
//            em.close();
            em.clear();
            session.getTransaction().begin();
        }
        session.createQuery("DELETE FROM Person").executeUpdate();
        session.getTransaction().commit();
        session.clear();
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
        assertNull(actualPerson);
    }
}