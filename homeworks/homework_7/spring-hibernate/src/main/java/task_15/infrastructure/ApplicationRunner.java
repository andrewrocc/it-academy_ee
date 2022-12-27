package task_15.infrastructure;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import task_15.infrastructure.config.ProjectConfig;
import task_15.infrastructure.model.Product;
import task_15.infrastructure.service.ProductService;

import java.math.BigDecimal;

public class ApplicationRunner {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        ProductService productService = context.getBean(ProductService.class);
        Product product = new Product(null, "Pencil", new BigDecimal("15.4"));
        System.out.println(product);
        productService.addNewProduct(product);
        Product isInDBProduct = productService.getProduct((short) 1);
        System.out.println(isInDBProduct);

        context.close();
    }
}
