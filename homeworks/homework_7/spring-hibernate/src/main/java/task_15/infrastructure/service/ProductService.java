package task_15.infrastructure.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task_15.infrastructure.dao.ProductDao;
import task_15.infrastructure.model.Product;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductDao productDao;

    @Transactional
    public void addNewProduct(Product p) {
        productDao.addProduct(p);
    }

    @Transactional(readOnly = true)
    public Product getProduct(short id) {
        return productDao.getById(id);
    }

    private void updateProductInfo(Product p) {
        addNewProduct(p);
    }

    @Transactional
    public void deleteProduct(Product p) {
        productDao.delete(p);
    }
}
