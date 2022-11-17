package service;

import my.first.service.SearchService;
import my.first.service.ServiceContextConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.http.HttpClient;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceContextConfiguration.class)
public class ServiceContextConfigurationTest {

    @Autowired
    SearchService searchService;

    @Autowired
    @Qualifier("simpleHttpClient")
    HttpClient httpClient;

    @Test
    public void searchServiceTest() {
        assertNotNull(searchService);
    }

    @Test
    public void httpClientTest() {
        assertNotNull(httpClient);
    }
}