package my.first.dao;

import lombok.SneakyThrows;
import my.first.model.Meeting;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
public class MeetingDaoImplTest extends BaseDaoTest {

    @Autowired
    MeetingDao targetObject;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
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
        DatabaseOperation.DELETE_ALL.execute(iDatabaseConnection, meetingDataSet);
    }
}