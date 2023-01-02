package my.first.dao.impl;

import my.first.dao.AppUserDao;
import my.first.dao.BaseDaoTest;
import my.first.model.AppUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class AppUserDaoImplTest extends BaseDaoTest {

    @Autowired
    AppUserDao targetObject;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        targetObject = null;
    }

    @Test
    public void findByUserName() {
        //when
        List<AppUser> userName = targetObject.findByUserName("root");

        //then
        assertNotNull(userName);
        assertEquals(1, userName.size());
    }
}