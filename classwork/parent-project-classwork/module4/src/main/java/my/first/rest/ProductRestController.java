package my.first.rest;

import my.first.model.ProductInfo;
import my.first.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<List<ProductInfo>> getAllProducts() {
        List<ProductInfo> all = productService.getAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }
}
