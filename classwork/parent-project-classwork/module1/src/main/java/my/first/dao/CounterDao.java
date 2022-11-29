package my.first.dao;

import my.first.model.Counter;

public interface CounterDao {

    Counter findById(long id);

    void update(Counter c);

    void create(Counter c);
}
