package my.first.service;

import my.first.model.ProductInfo;       // in pom.xml dependencies module1
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

@Service
public class SearchService {

    @Autowired
    @Qualifier("simpleHttpClient")
    private HttpClient httpClient;

    private static final List<ProductInfo> warehouse = new ArrayList<>(10);

    static {
        warehouse.add(getInstance(1, "Coffee Arabica", 3.5));
        warehouse.add(getInstance(2, "Coffee Espresso", 3.99));
        warehouse.add(getInstance(3, "Coffee Latte", 3.15));
        warehouse.add(getInstance(4, "Coffee Latte Big", 5.5));
        warehouse.add(getInstance(5, "Black tea", 2.5));
        warehouse.add(getInstance(6, "Water still", 1.75));
        warehouse.add(getInstance(7, "Water sparkling", 2.0));
        warehouse.add(getInstance(8, "Coca-cola", 4.10));
        warehouse.add(getInstance(9, "Sprite", 4.15));
        warehouse.add(getInstance(10, "Pepsi", 3.99));
    }

    public List<ProductInfo> searchProduct(String productName) {
        if(productName == null || productName.isEmpty()) {
            return Collections.emptyList();
        }
        if (productName.equals("all")) {
            return warehouse;
        }
        return warehouse.stream()
                .filter(x -> x.getName()
                        .toLowerCase()
                        .contains(productName.toLowerCase()))
                .toList();
    }

    /**
     * Factory method
     * @param id
     * @param name
     * @param price
     * @return
     */
    static ProductInfo getInstance(int id, String name, double price) {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setId(id);
        productInfo.setName(name);
        productInfo.setPrice(price);
        return  productInfo;
    }
}
