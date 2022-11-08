package by.it.tasks.practical_task_1.dao;

import by.it.pojos.Person;

public interface PersonDao {

    Person load(long id);

    Person get(long id);
}
