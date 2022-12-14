package my.first.dao.impl;

import my.first.dao.CounterDao;
import my.first.model.Counter;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CounterDaoImpl implements CounterDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Counter read(long id) {
        return sessionFactory.getCurrentSession().load(Counter.class, id, LockMode.PESSIMISTIC_WRITE);
    }

    public void update(Counter c) {
        sessionFactory.getCurrentSession().saveOrUpdate(c);
    }
}
