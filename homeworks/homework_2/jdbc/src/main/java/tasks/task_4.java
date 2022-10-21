package tasks;

import lombok.SneakyThrows;

import java.sql.*;

public class task_4 {

    private final Connection connection;

    public task_4(String[] param, Connection connection) {
        this.connection = connection;
        insertDataToDBreceiver(param);
        insertDataToDBexpenses(param);
    }

    @SneakyThrows
    public void insertDataToDBreceiver(String[] param) {
        int id = Integer.parseInt(param[0]);
        String insert_query = String.format("INSERT INTO receiver (num, name) VALUES (%d, '%s');", id, param[1]);
        Statement statement = connection.createStatement();
        var affectedRow = statement.executeUpdate(insert_query);
//        System.out.printf("Affected row %d\n", affectedRow);
        statement.close();
    }

    @SneakyThrows
    public void insertDataToDBexpenses(String[] param) {
        int id = Integer.parseInt(param[2]);
        int id_receiver = Integer.parseInt(param[0]);
        String insert_query = String.format("INSERT INTO expenses (num, paydate, receiver, value) VALUES(%d, '%s', %d, %s)",
                id, param[3], id_receiver, param[4]);
        Statement statement = connection.createStatement();
        var affectedRow = statement.executeUpdate(insert_query);
//        System.out.printf("Affected row %d\n", affectedRow);
        statement.close();
    }
}
