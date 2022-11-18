package service;

import my.first.dao.ProductInfoDaoImpl;
import my.first.service.SearchService;

import static org.junit.Assert.*;

public class SearchServiceTest {

    SearchService targetObject;

    @org.junit.Before
    public void setUp() throws Exception {
        targetObject = new SearchService(new ProductInfoDaoImpl());
    }

    @org.junit.After
    public void tearDown() throws Exception {
        targetObject = null;
    }

    @org.junit.Test
    public void searchProduct() {
        // given
        String searchPattern = "coffee";
        //when
        int result = targetObject.searchProduct(searchPattern).size();
        //then
        int expectedResult = 4;
        assertEquals(expectedResult, result);
    }

    @org.junit.Test
    public void searchEmptyProduct() {
        // given
        String searchPattern = "vodka";
        //when
        int result = targetObject.searchProduct(searchPattern).size();
        //then
        int expectedResult = 0;
        assertEquals(expectedResult, result);
    }

    @org.junit.Test
    public void searchNullPattern() {
        // given
        String searchPattern = null;
        //when
        int result = targetObject.searchProduct(searchPattern).size();
        //then
        int expectedResult = 0;
        assertEquals(expectedResult, result);
    }
}