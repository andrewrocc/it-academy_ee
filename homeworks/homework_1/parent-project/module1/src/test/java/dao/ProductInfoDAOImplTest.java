package dao;

import model.ProductInfo;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductInfoDAOImplTest {

    ProductInfoDAO targetObject;

    @org.junit.Before
    public void setUp() throws Exception {
        targetObject = new ProductInfoDAOImpl();
    }

    @org.junit.After
    public void tearDown() {
        targetObject = null;
    }

    @org.junit.Test
    public void create() {
    }

    @org.junit.Test
    public void readAll() {
        //given
        // TODO: add test data to test scheme

        //when
        List<ProductInfo> productInfo = targetObject.readAll();

        //then
        assertEquals(productInfo.size(), 10);
        // TODO: delete data test from test scheme
    }

    @org.junit.Test
    public void update() {
    }

    @org.junit.Test
    public void delete() {
    }
}