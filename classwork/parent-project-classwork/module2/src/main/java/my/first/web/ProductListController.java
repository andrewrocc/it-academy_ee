package my.first.web;

import lombok.RequiredArgsConstructor;
import my.first.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ProductListController {

    private final ProductService productService;

    @GetMapping("/product-list.html")
    public ModelAndView getProductList() {
        return new ModelAndView("product_list", Map.of("products", productService.getAll()));
    }
}
