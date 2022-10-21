package tasks;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class task_6 {

    private final Connection connection;

    public task_6(Connection connection) {
        this.connection = connection;
        printListReceiverAndSumPayment();
        printAmountOfPaymentsWhenWasMaximum();
        printMaximumPaymentWhenSumOfPaymentsWasMax();
    }

    @SneakyThrows
    private void printListReceiverAndSumPayment() {
        String select_query = "SELECT name, (SELECT SUM(value) FROM expenses e WHERE receiver = r.num) AS sum FROM receiver r;";
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery(select_query);
        while (set.next()) {
            System.out.printf("%18s - %10.2f %n", set.getString(1), set.getDouble(2));
        }

        set.close();
        statement.close();
    }

    @SneakyThrows
    private void printAmountOfPaymentsWhenWasMaximum() {
        String select_query = "SELECT paydate, SUM(value) AS sumAmount FROM expenses " +
                "GROUP BY paydate ORDER BY 2 DESC LIMIT 1;\n";
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery(select_query);
        while (set.next()) {
            System.out.printf("%s - %10.2f %n", set.getString(1), set.getDouble(2));
        }

        set.close();
        statement.close();
    }

    @SneakyThrows
    private void printMaximumPaymentWhenSumOfPaymentsWasMax() {
        String select_query = "SELECT paydate, MAX(value) FROM expenses WHERE paydate = " +
                "(SELECT d.paydate FROM  (SELECT paydate, SUM(value) AS sumAmount FROM expenses " +
                "GROUP BY paydate ORDER BY 2 DESC LIMIT 1) AS d) GROUP BY paydate;";
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery(select_query);
        while (set.next()) {
            System.out.printf("%s - %10.2f %n", set.getString(1), set.getDouble(2));
        }

        set.close();
        statement.close();
    }
}
