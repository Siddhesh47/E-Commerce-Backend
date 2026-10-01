package com.example.demo.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
import com.example.demo.repo.Productrepo;

@RestController
@RequestMapping("/admin")
public class AdminController {
private final Productrepo productrepo;
	
	public AdminController(Productrepo productrepo) {
		this.productrepo = productrepo;
	}
	
	@GetMapping("/")
	public String demo() {
		return "Hello Good Morning";
	}
	
	@GetMapping("/products")
	public List<Product> getProduct(){
		List<Product> products = this.productrepo.findAll();
		return products;
	}
	
	@PostMapping("/add")
	public Product addProduct(@RequestBody Product product) {
		return productrepo.save(product);
		
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id) {
		this.productrepo.deleteById(id);
		return new ResponseEntity<String>("product deleted success fully",HttpStatusCode.valueOf(200));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateProduct(@RequestBody Product product,@PathVariable int id) {

		Product product2 = this.productrepo.getById(id);
		product2.setName(product.getName());
		product2.setCategory(product.getCategory());
		product2.setDiscount(product.getDiscount());
		product2.setPrice(product.getPrice());
		product2.setQuantity(product.getQuantity());
		
		this.productrepo.save(product2);
		return new ResponseEntity<String>("data updates",HttpStatusCode.valueOf(200));
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
