package by.it.tasks.task_3.dao;

import org.hibernate.LockMode;

public interface PersonDaoSynchronize {

    void flushAndClearSession();

    void synchronizeSessionWithDB(Object obj, LockMode lockMode);

    void flushAndClearEntityManager();

    void synchronizeEntityManagerWithDB();
}
