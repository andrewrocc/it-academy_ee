import tasks.task_4;
import tasks.task_5;
import tasks.task_6;
import task_7.dao.DAOImpl;
import lombok.SneakyThrows;
import task_7.model.Expenses;
import task_7.model.Receiver;
import jdbc.config.SetupJDBC;

import java.sql.*;
import java.util.List;
import java.util.Arrays;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ApplicationRunner {

    private static Connection connection = null;

    private static Statement statement = null;

    /**
     * before u run this program need to set up initial parameters<p>
     * Example( 101 "Lee Sin" 101 "2022-12-01" 250.0 )<p>
     * @param args array of parameters where:<p>
     *             in table receiver<p>
     *             args[0] 101 - id column,<p>
     *             args[1] "Lee Sin" - name column,<p>
     * <p>
     *             in table expenses<p>
     *             args[2] 101 - id column,<p>
     *             args[3] "2022-12-01" - paydate column,<p>
     *             args[4] 250.0 - value column.<p>
     *
     */
    @SneakyThrows
    public static void main(String[] args) {
        if (args.length < 5) {
            System.err.println("The initial parameters of program are null or have no enough elements.");
            return;
        }
        if (!isValidDateFormat(args[3])) {
            System.err.println("Invalid date format, required (yyyy-MM-dd).");
            return;
        }
        if (args[4].contains(",")) {
            args[4] = args[4].replace(',', '.');
        }

        SetupJDBC setup = new SetupJDBC();
        connection = setup.getConnection();

        System.out.println("task 4:");
        new task_4(args, connection);
        printAllReceiver();
        deleteInsertion(args);

        System.out.println("\ntask 5:");
        new task_5(args, connection);
        printAllReceiver();
        deleteInsertion(args);

        System.out.println("\ntask 6:");
        new task_6(connection);

        System.out.println("\ntask 7:");
        DAOImpl dao = new DAOImpl();
        Receiver receiver = dao.getReceiver(1);
        System.out.println("dao.getReceiver: " + receiver);
        Expenses expenses = dao.getExpense(1);
        System.out.println("\ngetExpense: " + expenses);
        List<Receiver> listReceivers = dao.getReceivers();
        System.out.println("\ngetReceivers: " + Arrays.toString(listReceivers.toArray()).replace("), ", "\"), \n"));
        List<Expenses> listExpenses = dao.getExpenses();
        System.out.println("\ngetExpenses: " + Arrays.toString(listExpenses.toArray()).replace("), ", "\"), \n"));

        connection.close();
    }

    private static boolean isValidDateFormat(String date) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
        } catch (ParseException ex) {
            return false;
        }
        return true;
    }

    @SneakyThrows
    public static void printAllReceiver() {
        statement = connection.createStatement();
        String query = "SELECT paydate, value, name FROM expenses, receiver WHERE receiver = receiver.num;";
        ResultSet set = statement.executeQuery(query);
        while(set.next()) {
            String padded = String.format("%s - %8s - %20s\n", set.getString(1), set.getString(2),
                    set.getString(3));
            System.out.printf(padded);
        }
        set.close();
        statement.close();
    }

    @SneakyThrows
    public static void deleteInsertion(String[] param) {
        statement = connection.createStatement();
        String queryReceiver = "DELETE FROM receiver WHERE num=" + param[0] + ";";
        statement.executeUpdate(queryReceiver);
        statement.close();
    }
}
