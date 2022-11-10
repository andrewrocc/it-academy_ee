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
import org.junit.*;

import java.io.Serializable;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PersonDaoImplTest {

    private static Session session;

    PersonDaoImpl targetObject;

    private final Logger log = Logger.getLogger(PersonDaoImplTest.class.getName());

    @Before
    public void setUp() throws Exception {
        targetObject = new PersonDaoImpl(session);
        fillDataInDB();
//        session = !session.isOpen() ? HibernateUtil.getSession() : session;
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

    @BeforeClass
    public static void beforeSetup() {
        session = HibernateUtil.getSession();
    }

    @After
    public void tearDown() throws Exception {
        targetObject = null;
    }

    @AfterClass
    public static void destroy() {
        if (!session.getTransaction().isActive()) {
            session.getTransaction().begin();
        }
        session.createQuery("DELETE FROM Person").executeUpdate();
        session.getTransaction().commit();
        session.getEntityManagerFactory().close();
        session.close();
    }

    @Test
    @SneakyThrows
    public void create() {
        //given
        long queryResult = (Long) session.createQuery("SELECT count(*) FROM Person").getSingleResult();
        assertEquals(3L, queryResult);

        //when
        Person johnDoe = new Person(null, 30, "JOHN", "Doe");
        targetObject.create(johnDoe);
        session.persist(johnDoe);
        assertTrue(session.contains(johnDoe));
//        for (long i = 1;; i++) {
//            Object entity = getFromSession(i, Person.class, session);
//            if (entity != null) {
//                log.info(entity.toString());
//            } else {
//                break;
//            }
//        }

        //then
        long actualSize = (Long) session.createQuery("SELECT count(*) FROM Person").getSingleResult();
        assertEquals(4L, actualSize);
    }

    public static Object getFromSession(Serializable identifier, Class<?> clazz, Session s) {
        String entityName = clazz.getName();
        if (identifier == null) {
            return null;
        }
        SessionImplementor sessionImpl = (SessionImplementor) s;
        EntityPersister entityPersister = sessionImpl.getFactory().getEntityPersister(entityName);
        PersistenceContext persistenceContext = sessionImpl.getPersistenceContext();
        EntityKey entityKey = new EntityKey(identifier, entityPersister);
        Object entity = persistenceContext.getEntity(entityKey);
        return entity;
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
        long queryResult = (Long) session.createQuery("SELECT count(*) FROM Person").getSingleResult();
        assertEquals(4L, queryResult);

        //when
        targetObject.delete(p);

        //then
        long actualSize = (Long) session.createQuery("SELECT count(*) FROM Person").getSingleResult();
        assertEquals(3L, actualSize);
    }
}