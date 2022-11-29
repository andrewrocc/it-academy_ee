package my.first.dao;

import my.first.model.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

@Repository
@Scope(SCOPE_SINGLETON)
public class Concurrency extends Thread {

    @Autowired
    private CounterDao counterDao;

    public void updateCount() {
        Counter counter = counterDao.findById(0L);
        int count = counter.getCount();
        System.out.println("current count = " + counter.getCount() + ", current thread = " + Thread.currentThread().getId());
//		count++;
        counter.setCount(++count);
        System.out.println("new count = " + counter.getCount());
        counterDao.update(counter);
    }
}
