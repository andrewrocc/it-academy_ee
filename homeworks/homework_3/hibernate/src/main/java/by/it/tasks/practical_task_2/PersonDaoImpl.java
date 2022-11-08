package by.it.tasks.practical_task_2;

import by.it.pojos.Person;
import org.hibernate.Session;
import by.it.util.HibernateUtil;
import org.hibernate.Transaction;
import by.it.tasks.task_3.dao.PersonDaoSynchronizeImpl;

public class PersonDaoImpl implements PersonDao {

    private final Session session;

    private final PersonDaoSynchronizeImpl sessionDaoSynchronize;

    public PersonDaoImpl() {
        session = HibernateUtil.getSession();
        sessionDaoSynchronize = new PersonDaoSynchronizeImpl(session);
    }

    @Override
    public void deleteEntity(Person p) {
        deleteEntity(Person.class.getName(), p);
    }

    @Override
    public boolean deleteEntity(String entityName, Person p) {
        Transaction transaction = session.beginTransaction();
        try {
            var deletePerson = session.find(Person.class, p.getId());
            if (deletePerson != null) {
                session.delete(session.contains(p) ? session : session.merge(entityName, p));
                sessionDaoSynchronize.flushAndClearSession();
                transaction.commit();
            }
            return true;
        } catch (Exception ex) {
            if (transaction != null) transaction.rollback();
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void createEntity(Person p) {
        createEntity(Person.class.getName(), p);
    }

    @Override
    public boolean createEntity(String entityName, Person p) {
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(entityName, p);
            sessionDaoSynchronize.flushAndClearSession();
            transaction.commit();
            return true;
        } catch (Exception ex) {
            if (transaction != null) transaction.rollback();
            return false;
        }
    }
}
