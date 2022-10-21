package jdbc.config;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

public class SetupJDBC {

    private static final String PASSWORD_DB_CONNECTION = "rootroot";

    private static final String USERNAME_DB_CONNECTION = "root";

    @SneakyThrows
    public SetupJDBC() {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    @SneakyThrows
    public Connection getConnection() {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ListExpenses?createDatabaseIfNotExist=true&serverTimezone=UTC",
                USERNAME_DB_CONNECTION, PASSWORD_DB_CONNECTION);
    }
}
