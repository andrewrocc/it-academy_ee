package by.it.tasks.task_2.dao;

import by.it.pojos.Person;
import by.it.tasks.task_3.dao.PersonDaoSynchronizeImpl;
import by.it.util.HibernateUtil;
import org.hibernate.Session;

public class PersonDaoImpl implements PersonDao {

    private final Session session;

    private final PersonDaoSynchronizeImpl sessionDaoSynchronize;

    public PersonDaoImpl() {
        session = HibernateUtil.getSession();
        sessionDaoSynchronize = new PersonDaoSynchronizeImpl(session);
    }

    @Override
    public void create(Person p) {
        Person person = new Person(null, p.getAge(), p.getName(), p.getSurname());
        session.beginTransaction();
        session.saveOrUpdate(person);
        session.getTransaction().commit();
        sessionDaoSynchronize.flushAndClearSession();
    }

    @Override
    public Person findById(Long id) {
        return session.load(Person.class, id);
    }

    @Override
    public void update(Person p) {
        create(p);
    }

    @Override
    public void delete(Person p) {
        session.beginTransaction();
        Person deleteP = session.get(Person.class, p.getId());
        if (deleteP != null) {
            session.delete(deleteP);
            session.getTransaction().commit();
            sessionDaoSynchronize.flushAndClearSession();
        }
    }
}
