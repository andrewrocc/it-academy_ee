package my.first.web;

import my.first.model.ProductInfo;
import my.first.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/add-product.html")
    public String getProductForm() {
        return "add_product";
    }


    @PostMapping("/add-product.html")
    public String addProduct(ProductInfo product) {
        System.out.println(product);
        productService.addNewProduct(product);
        return "redirect:/index.html";
    }
}
