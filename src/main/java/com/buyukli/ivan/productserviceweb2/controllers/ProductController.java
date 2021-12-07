package com.buyukli.ivan.productserviceweb2.controllers;

import com.buyukli.ivan.productserviceweb2.dto.ProductDto;
import com.buyukli.ivan.productserviceweb2.entities.Product;
import com.buyukli.ivan.productserviceweb2.exceptions.CustomRuntimeNotFoundException;
import com.buyukli.ivan.productserviceweb2.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_cost", required = false) String minCost,
            @RequestParam(name = "max_cost", required = false) String maxCost,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
        if (page < 1) {
            page = 1;
        }
        return productService.find(minCost, maxCost, titlePart, page).map(
                ProductDto::new
        );
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.findById(id).orElseThrow(() -> new CustomRuntimeNotFoundException("Product not found " + id));
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product){
        product.setId(null);
        return productService.save(product);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product){
        return productService.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id){
        productService.deleteById(id);
    }

//    @GetMapping("/products/sortBy/{field}")
//    public List<Product> getAllProducts(@PathVariable String field){
//
//        return productService.findProductsWithSorting(field);
//    }

}
