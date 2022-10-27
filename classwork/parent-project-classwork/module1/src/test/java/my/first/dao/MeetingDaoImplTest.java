package my.first.dao;

import lombok.SneakyThrows;
import my.first.model.Meeting;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.*;

public class MeetingDaoImplTest extends BaseDaoTest {

    MeetingDaoImpl targetObject;

    @Before
    public void setUp() throws Exception {
        targetObject = new MeetingDaoImpl(testSessionFactory);
    }

    @After
    public void tearDown() throws Exception {
        targetObject = null;
    }

    @Test
    @SneakyThrows
    public void findAll() {
        //given
        Connection connection = testMysqlJdbcDataSource.getConnection();

        IDataSet meetingDataSet = new FlatXmlDataSetBuilder()
                .build(MeetingDaoImplTest.class.getResourceAsStream("MeetingDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, meetingDataSet);

        //when
        List<Meeting> meetingList = targetObject.findAll();

        //then
        assertEquals(2, meetingList.size());
        for (var e : meetingList) {
            assertTrue(e.getEmployees().size() > 0);
        }
    }
}