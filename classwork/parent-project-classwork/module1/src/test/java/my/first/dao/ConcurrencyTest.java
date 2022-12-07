package my.first.dao;

import lombok.SneakyThrows;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class ConcurrencyTest extends BaseDaoTest {

    @Autowired
    Concurrency targetObject;

    @Before
    public void setUp() throws Exception {
        Connection conn = testMysqlJdbcDataSource.getConnection();
        conn.createStatement().executeUpdate("insert into Counter values (0, 0);");
    }

    @After
    public void tearDown() throws Exception {
        Connection conn = testMysqlJdbcDataSource.getConnection();
        conn.createStatement().executeUpdate("delete from Counter;");
        conn.close();
        targetObject = null;
    }

    @Test
    @SneakyThrows
    public void run() {
        //Given
        Connection conn = testMysqlJdbcDataSource.getConnection();
        conn.setAutoCommit(true);
        conn.createStatement().executeUpdate("update Counter set count=0 where id=0;");
        ResultSet rs = conn.createStatement().executeQuery("select count from Counter where id=0;");
        rs.next();
        int initialCount = rs.getInt(1);
        assertEquals(0, initialCount);

        //When
        for (int i = 0; i < 10; i++) {
            new Thread(() -> targetObject.updateCount()).start();
        }
		Thread.sleep(2000);

        //Then
        rs = conn.createStatement().executeQuery("select count from Counter where id=0;");
        rs.next();
        initialCount = rs.getInt(1);
        assertEquals(10, initialCount);
    }
}