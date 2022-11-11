package by.it.tasks.task_3.dao;

import org.hibernate.LockMode;

public interface PersonDaoSynchronize {

    void flushAndClearSession();

    void synchronizeSession(Object obj, LockMode lockMode);

    void flushAndClearEntityManager();

    void synchronizeEntityManager();
}
