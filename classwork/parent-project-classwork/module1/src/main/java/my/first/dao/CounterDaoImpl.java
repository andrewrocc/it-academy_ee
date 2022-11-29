package my.first.dao;

import my.first.model.Counter;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CounterDaoImpl implements CounterDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void create(Counter c) {
        sessionFactory.getCurrentSession().saveOrUpdate(c);
    }

    public Counter findById(long id) {
        return sessionFactory.getCurrentSession().load(Counter.class, id, LockMode.PESSIMISTIC_WRITE);
    }

    public void update(Counter c) {
        create(c);
    }
}
