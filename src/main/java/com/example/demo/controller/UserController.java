package com.example.demo.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.repo.Productrepo;
import com.example.demo.repo.UserRepo;

@RestController
@RequestMapping("/user")
public class UserController {
	private UserRepo userRepo;
	private final Productrepo productrepo;
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User user) {

		System.out.println("USERNAME: " + user.getUsername());
	    System.out.println("EMAIL: " + user.getEmail());
	    System.out.println("PASSWORD: " + user.getPassword());
	    System.out.println("ROLE: " + user.getRole());
	    
	    
	    user.setPassword(
	        passwordEncoder.encode(user.getPassword())
	    );

	    userRepo.save(user);

	    return ResponseEntity.ok("User registered successfully");
	}

	
	
	public UserController(Productrepo productrepo) {
		super();
		this.productrepo = productrepo;
		// TODO Auto-generated constructor stub
	}


	@GetMapping("/products")
	public List<Product> getProduct(){
		List<Product> products = this.productrepo.findAll();
		return products;
	}
	
	@GetMapping("/prodcuts/{id}")
	public Product getProduct(@PathVariable int id) {
		return this.productrepo.findById(id).get();
	}
	
//	@GetMapping("/products/category/{category}")
//	public List<Product> getProductsByCategory(@PathVariable String category) {
//	    return productrepo.findByCategory(category);
//	}
	
	@GetMapping("/products/search")
	public List<Product> searchProductByName(@RequestParam(value = "q",required = false) String q){
		return this.productrepo.findByNameContaining(q);
	}
	
	@GetMapping("/products/price")
	public List<Product> getProductByPriceRange(@RequestParam(value = "min",required = false)double min,@RequestParam(value = "max",required = false)double max){
		return this.productrepo.findByPriceBetween(min, max);
	}
	
	@GetMapping("/products/category/{name}")
	public List<Product> filterProductByPrice(
	        @PathVariable String name,
	        @RequestParam(value = "by", required = false, defaultValue = "price") String by,
	        @RequestParam(value = "order", required = false, defaultValue = "asc") String order) {

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
	public List<Product> findProductInStock(@RequestParam(value = "value",required = false)int value){
		return this.productrepo.findByQuantityGreaterThanEqual(value);
	}
}
