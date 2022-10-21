package task_7.dao;

import task_7.model.Expenses;
import task_7.model.Receiver;

import java.util.List;

public interface DAO {

    Receiver getReceiver(int num);

    List<Receiver> getReceivers();

    Expenses getExpense(int num);

    List<Expenses> getExpenses();

    int addReceiver(Receiver rec);

    int addExpense(Expenses exp);
}
