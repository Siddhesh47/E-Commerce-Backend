package com.example.demo.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.repo.Productrepo;
import com.example.demo.repo.UserRepo;

@RestController
@RequestMapping("/auth")
public class TestController {

    private final Productrepo productrepo;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public TestController(Productrepo productrepo,
                           UserRepo userRepo,
                           PasswordEncoder passwordEncoder) {
        this.productrepo = productrepo;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String demo() {
        return "Hello Good Morning";
    }

    @GetMapping("/products")
    public List<Product> getProduct() {
        return productrepo.findAll();
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        return productrepo.save(product);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        productrepo.deleteById(id);
        return new ResponseEntity<>(
                "Product deleted successfully",
                HttpStatusCode.valueOf(200)
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProduct(
            @RequestBody Product product,
            @PathVariable int id) {

        Product product2 = productrepo.getById(id);

        product2.setName(product.getName());
        product2.setCategory(product.getCategory());
        product2.setDiscount(product.getDiscount());
        product2.setPrice(product.getPrice());
        product2.setQuantity(product.getQuantity());

        productrepo.save(product2);

        return new ResponseEntity<>(
                "Data updated",
                HttpStatusCode.valueOf(200)
        );
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable int id) {
        return productrepo.findById(id).get();
    }

    @GetMapping("/products/search")
    public List<Product> searchProductByName(
            @RequestParam(value = "q", required = false) String q) {

        return productrepo.findByNameContaining(q);
    }

    @GetMapping("/products/price")
    public List<Product> getProductByPriceRange(
            @RequestParam(value = "min") double min,
            @RequestParam(value = "max") double max) {

        return productrepo.findByPriceBetween(min, max);
    }

    @GetMapping("/products/category/{name}")
    public List<Product> filterProductByPrice(
            @PathVariable String name,
            @RequestParam(value = "by", defaultValue = "price") String by,
            @RequestParam(value = "order", defaultValue = "asc") String order) {

        List<Product> products = productrepo.findByCategory(name);

        Comparator<Product> comparator;

        switch (by.toLowerCase()) {

            case "price":
                comparator = Comparator.comparing(Product::getPrice);
                break;

            case "quantity":
                comparator = Comparator.comparing(Product::getQuantity);
                break;

            case "discount":
                comparator = Comparator.comparing(Product::getDiscount);
                break;

            case "name":
                comparator = Comparator.comparing(Product::getName);
                break;

            default:
                comparator = Comparator.comparing(Product::getPrice);
        }

        if ("desc".equalsIgnoreCase(order)) {
            comparator = comparator.reversed();
        }

        products.sort(comparator);

        return products;
    }

    @GetMapping("/products/search/stock")
    public List<Product> findProductInStock(
            @RequestParam int value) {

        return productrepo.findByQuantityGreaterThanEqual(value);
    }

    // REGISTER USER
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        userRepo.save(user);

        return ResponseEntity.ok("User registered successfully");
    }
}
