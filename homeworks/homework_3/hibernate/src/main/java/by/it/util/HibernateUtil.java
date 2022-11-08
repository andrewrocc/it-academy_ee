package by.it.util;

import org.hibernate.Session;
import org.jboss.logging.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class HibernateUtil {

    private static final Logger log = LoggerFactory.logger(HibernateUtil.class);

    private static final EntityManagerFactory emFactory;

    private static SessionFactory sessionFactory;

    static {
        emFactory = Persistence.createEntityManagerFactory("by.it");
        Session session = emFactory.createEntityManager().unwrap(Session.class);
        sessionFactory = session.getSessionFactory();
    }

    private HibernateUtil() {
        try {
            Configuration configuration = new Configuration().configure();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        } catch (Throwable ex) {
            log.error("initial sessionFactory creation failed. " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }

    public static void close() {
        emFactory.close();
    }
}
