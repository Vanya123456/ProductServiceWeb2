package com.buyukli.ivan.productserviceweb2.controllers;

import com.buyukli.ivan.productserviceweb2.entities.Product;
import com.buyukli.ivan.productserviceweb2.exceptions.CustomRuntimeNotFoundException;
import com.buyukli.ivan.productserviceweb2.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.findById(id).orElseThrow(() -> new CustomRuntimeNotFoundException("Product not found " + id));
    }

    @GetMapping("products/delete/{id}")
    public void deleteProductById(@PathVariable Long id){
        productService.deleteById(id);
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product){
        return productService.save(product);
    }

    @GetMapping("/products/price_between")
    public List<Product> findProductsByPriceBetween(@RequestParam(defaultValue = "0") String min, @RequestParam(defaultValue = "5") String max) {
        return productService.findProductsByPriceBetween(min, max);
    }

    @GetMapping("/products/sortBy/{field}")
    public List<Product> getAllProducts(@PathVariable String field){

        return productService.findProductsWithSorting(field);
    }

    @GetMapping("/products/pagination/{offset}/{size}")
    public Page<Product> getAllProducts(@PathVariable Integer offset, @PathVariable Integer size){

        return productService.findProductsWithPagination(offset, size);
    }

}
