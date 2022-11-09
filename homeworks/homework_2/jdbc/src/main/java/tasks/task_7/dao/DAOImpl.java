package tasks.task_7.dao;

import jdbc.config.SetupJDBC;
import lombok.SneakyThrows;
import tasks.task_7.model.Expenses;
import tasks.task_7.model.Receiver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOImpl implements DAO {

    private Connection connection;

    private Statement statement;

    public DAOImpl() {
        SetupJDBC setup = new SetupJDBC();
        connection = setup.getConnection();
    }

    @Override
    @SneakyThrows
    public Receiver getReceiver(int num) {
        statement = connection.createStatement();
        String query = "SELECT * FROM receiver WHERE num=" + num;
        ResultSet set = statement.executeQuery(query);
        set.next();
        Receiver rec = new Receiver();
        rec.setNum(set.getInt(1));
        rec.setName(set.getString(2));
        set.close();
        statement.close();
        return rec;
    }

    @Override
    @SneakyThrows
    public List<Receiver> getReceivers() {
        statement = connection.createStatement();
        String query = "SELECT * FROM receiver";
        ResultSet set = statement.executeQuery(query);
        List<Receiver> recList = new ArrayList<>();
        while (set.next()) {
            final Receiver receiver = new Receiver();
            receiver.setNum(set.getInt(1));
            receiver.setName(set.getString(2));
            recList.add(receiver);
        }

        set.close();
        statement.close();
        return recList;
    }

    @Override
    @SneakyThrows
    public Expenses getExpense(int num) {
        statement = connection.createStatement();
        String query = "SELECT * FROM expenses WHERE num=" + num;
        ResultSet set = statement.executeQuery(query);
        set.next();
        Expenses exp = new Expenses();
        exp.setNum(set.getInt(1));
        exp.setPayDate(set.getDate(2));
        exp.setReceiver(set.getInt(3));
        exp.setValue(set.getDouble(4));
        set.close();
        statement.close();
        return exp;
    }

    @Override
    @SneakyThrows
    public List<Expenses> getExpenses() {
        statement = connection.createStatement();
        String query = "SELECT * FROM expenses";
        ResultSet set = statement.executeQuery(query);
        List<Expenses> expList = new ArrayList<>();
        while (set.next()) {
            final Expenses expenses = new Expenses();
            expenses.setNum(set.getInt(1));
            expenses.setPayDate(set.getDate(2));
            expenses.setReceiver(set.getInt(3));
            expenses.setValue(set.getDouble(4));
            expList.add(expenses);
        }
        set.close();
        statement.close();
        return expList;
    }

    @Override
    @SneakyThrows
    public int addReceiver(Receiver rec) {
        String insert_query = "INSERT INTO receiver (num, name) VALUES (?, ?);";
        PreparedStatement statement = connection.prepareStatement(insert_query);
        statement.setInt(1, rec.getNum());
        statement.setString(2, rec.getName());
        var affectedRow = statement.executeUpdate();
        statement.close();
        return affectedRow;
    }

    @Override
    @SneakyThrows
    public int addExpense(Expenses exp) {
        String insert_query = "INSERT INTO expenses (num, paydate, receiver, value) VALUES(?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(insert_query);
        statement.setInt(1, exp.getNum());
        statement.setDate(2, exp.getPayDate());
        statement.setInt(3, exp.getReceiver());
        statement.setDouble(4,exp.getValue());
        var affectedRow = statement.executeUpdate();
        statement.close();
        return affectedRow;
    }
}
