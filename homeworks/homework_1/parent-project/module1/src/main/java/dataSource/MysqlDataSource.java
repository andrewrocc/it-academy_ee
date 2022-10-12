package dataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class MysqlDataSource {

    public MysqlDataSource() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
                "root", "rootroot");
    }
}
