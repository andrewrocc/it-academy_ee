package tasks;

import lombok.SneakyThrows;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class task_5 {

    private final Connection connection;

    public task_5(String[] param, Connection connection) {
        this.connection = connection;
        insertDataToDBreceiver(param);
        insertDataToDBexpenses(param);
    }

    @SneakyThrows
    public void insertDataToDBreceiver(String[] param) {
        String insert_query = "INSERT INTO receiver (num, name) VALUES (?, ?);";
        PreparedStatement statement = connection.prepareStatement(insert_query);
        statement.setInt(1, Integer.parseInt(param[0]));
        statement.setString(2, param[1]);
        var affectedRow = statement.executeUpdate();
//        System.out.printf("Affected row %d\n", affectedRow);
        statement.close();
    }

    @SneakyThrows
    public void insertDataToDBexpenses(String[] param) {
        String insert_query = "INSERT INTO expenses (num, paydate, receiver, value) VALUES(?, ?, ?, ?)";
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date paramDate = formatter.parse(param[3]);
        Date sqlFormatDate = new Date(paramDate.getTime());
        PreparedStatement statement = connection.prepareStatement(insert_query);
        statement.setInt(1, Integer.parseInt(param[2]));
        statement.setDate(2, sqlFormatDate);
        statement.setInt(3, Integer.parseInt(param[0]));
        statement.setDouble(4, Double.parseDouble(param[4]));
        var affectedRow = statement.executeUpdate();
//        System.out.printf("Affected row %d\n", affectedRow);
        statement.close();
    }
}
