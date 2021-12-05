package com.buyukli.ivan.productserviceweb2.services;

import com.buyukli.ivan.productserviceweb2.entities.Product;
import com.buyukli.ivan.productserviceweb2.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public List<Product> findProductsByPriceBetween(String min, String max) {
        return productRepository.findProductByCostBetween(min, max);
    }

    public List<Product> findProductsWithSorting(String field){
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    public Page<Product> findProductsWithPagination(int offset, int pageSize){
        return productRepository.findAll(PageRequest.of(offset, pageSize));
    }
}
