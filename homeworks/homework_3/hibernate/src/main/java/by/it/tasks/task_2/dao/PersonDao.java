package by.it.tasks.task_2.dao;

import by.it.pojos.Person;

public interface PersonDao {

    void create(Person p);

    Person findById(Long id);

    void update(Person p);

    void delete(Person p);
}
