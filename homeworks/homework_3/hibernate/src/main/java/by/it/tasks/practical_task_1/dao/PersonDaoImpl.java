package by.it.tasks.practical_task_1.dao;

import by.it.pojos.Person;
import by.it.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PersonDaoImpl implements PersonDao {

    private final Session session;

    public PersonDaoImpl() {
        session = HibernateUtil.getSession();
    }

    public PersonDaoImpl(Session session) {
        this.session = session;
    }

    @Override
    public Person load(long id) {
        Transaction transaction = session.beginTransaction();
        try {
            Person p = session.load(Person.class, id);
            transaction.commit();
            return p;
        } catch (Exception ex) {
            if (transaction != null) transaction.rollback();
            throw ex;
        }
    }

    @Override
    public Person get(long id) {
        Transaction transaction = session.beginTransaction();
        try {
            Person p = session.get(Person.class, id);
            transaction.commit();
            return p;
        } catch (Exception ex) {
            if (transaction != null) transaction.rollback();
            throw ex;
        }
    }
}
