package by.it.tasks.task_2.util;

import by.it.pojos.Person;
import by.it.tasks.task_2.dao.PersonDaoImpl;
import by.it.util.HibernateUtil;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.engine.spi.EntityKey;
import org.hibernate.engine.spi.PersistenceContext;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.persister.entity.EntityPersister;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class PersonDaoImplTest {

    private static Session session;

    private static EntityManager em;

    PersonDaoImpl targetObject;

    private final Logger log = Logger.getLogger(PersonDaoImplTest.class.getName());

    @Before
    public void setUp() throws Exception {
        session = HibernateUtil.getSession();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("by.it");
        em = emf.createEntityManager();
        targetObject = new PersonDaoImpl(session);
        fillDataInDB();
    }

    public void fillDataInDB() {
        Person donaldTrump = new Person(null, 100, "Donald", "Trump");
        Person henryFord = new Person(null, 55, "Henry", "Ford");
        Person namelessPerson = new Person(null, 20, "none", "none");
        session.getTransaction().begin();
        session.saveOrUpdate(donaldTrump);
        session.saveOrUpdate(henryFord);
        session.saveOrUpdate(namelessPerson);
        session.getTransaction().commit();
    }

    @After
    public void tearDown() throws Exception {
        targetObject = null;
        if (!session.getTransaction().isActive() && em.isOpen()) {
            em.getEntityManagerFactory().close();
            em.close();
            session.getTransaction().begin();
        }
        session.createQuery("DELETE FROM Person").executeUpdate();
        session.getTransaction().commit();
        session.clear();
    }

    @Test
    @SneakyThrows
    public void create() {
        //given
        long queryResult = (Long) session.createQuery("SELECT count(*) FROM Person").getSingleResult();
        assertEquals(3L, queryResult);

        //when
        Person johnDoe = new Person(null, 101, "JOHN", "Doe");
        targetObject.create(johnDoe);

//        assertEquals("john", targetObject.findById(4L).getName());
//        assertEquals("john", session.get(Person.class, 4L).getName());
        //then
        long actualSize = (Long) session.createQuery("SELECT count(*) FROM Person").getSingleResult();
        assertEquals(4L, actualSize);
    }

    private static Object getFromSession(Serializable identifier, Class<?> clazz, Session s) {
        String entityName = clazz.getName();
        if (identifier == null) {
            return null;
        }
        SessionImplementor sessionImpl = (SessionImplementor) s;
        EntityPersister entityPersister = sessionImpl.getFactory().getEntityPersister(entityName);
        PersistenceContext persistenceContext = sessionImpl.getPersistenceContext();
        EntityKey entityKey = new EntityKey(identifier, entityPersister);
        return persistenceContext.getEntity(entityKey);
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
        Person p = targetObject.findById(1L);
        long queryResult = (Long) session.createQuery("SELECT count(*) FROM Person").getSingleResult();
        assertEquals(3L, queryResult);

        //when
        targetObject.delete(p);

        //then
        long actualSize = (Long) session.createQuery("SELECT count(*) FROM Person").getSingleResult();
        assertEquals(2L, actualSize);
    }
}