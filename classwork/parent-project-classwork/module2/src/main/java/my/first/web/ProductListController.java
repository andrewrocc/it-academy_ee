package my.first.web;

import lombok.RequiredArgsConstructor;
import my.first.model.ProductInfo;
import my.first.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ProductListController {

    private final ProductService productService;

    @GetMapping("/product-list.html")
    public ModelAndView getProductList() {
//        return new ModelAndView("product_list", Map.of("products", productService.getAll()));
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List> products =
                restTemplate.getForEntity("http://localhost:8080/rest/products", List.class);

        return new ModelAndView("product_list", Map.of("products", products.getBody()));
    }
}
