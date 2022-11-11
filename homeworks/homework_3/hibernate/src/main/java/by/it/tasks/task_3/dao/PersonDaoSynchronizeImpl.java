package by.it.tasks.task_3.dao;

import com.sun.istack.Nullable;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Session;

import javax.persistence.Cache;
import javax.persistence.EntityManager;

/**
 * did not create separate test class, added this class to
 * @see by/it/tasks/task_2/util/PersonDaoImplTest
 */
public class PersonDaoSynchronizeImpl implements PersonDaoSynchronize {

    private final Session session;

    private final EntityManager em;

    public PersonDaoSynchronizeImpl(Session session) {
        this.session = session;
        em = null;
    }

    public PersonDaoSynchronizeImpl(EntityManager em) {
        this.em = em;
        session = null;
    }

    @Override
    public void flushAndClearSession() {
        if (session != null) {
            if (session.isDirty()) {
                session.flush();
                session.clear();
            }
        }
    }

    @Override
    public void synchronizeSession(Object obj, @Nullable LockMode lockMode) {
        if (session != null) {
            if (lockMode != null) {
                session.refresh(obj, new LockOptions(lockMode));
            } else {
                session.refresh(obj);
            }
        }
    }

    @Override
    public void flushAndClearEntityManager() {
        if (em != null && em.isOpen()) {
            em.flush();
            em.clear();
        }
    }

    @Override
    public void synchronizeEntityManager() {
        if (em != null && em.isOpen()) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().commit();
            }
            final Cache cache = em.getEntityManagerFactory().getCache();
            if (cache != null) {
                cache.evictAll();
            }
        }
    }
}
