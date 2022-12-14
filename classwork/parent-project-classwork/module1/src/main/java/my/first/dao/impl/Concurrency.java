package my.first.dao.impl;

import my.first.model.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

@Repository
@Scope(SCOPE_SINGLETON)
public class Concurrency {

    @Autowired
    private CounterDaoImpl counterDao;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateCount() {
        Counter counter = counterDao.read(0L);
        int count = counter.getCount();
        System.out.println("current count = " + counter.getCount() + ", current thread = " + Thread.currentThread().getId());
        counter.setCount(++count);
        System.out.println("new count = " + counter.getCount());
        counterDao.update(counter);
    }
}
