package by.it.tasks.practical_task_2;

import by.it.pojos.Person;

public interface PersonDao {

    void deleteEntity(Person p);

    boolean deleteEntity(String entityName, Person p);

    void createEntity(Person p);

    boolean createEntity(String entityName, Person p);
}
