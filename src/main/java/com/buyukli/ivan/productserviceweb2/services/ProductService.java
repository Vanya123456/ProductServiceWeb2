package com.buyukli.ivan.productserviceweb2.services;

import com.buyukli.ivan.productserviceweb2.entities.Product;
import com.buyukli.ivan.productserviceweb2.repositories.ProductRepository;
import com.buyukli.ivan.productserviceweb2.repositories.specifications.ProductSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> find(String minCost, String maxCost, String partTitle, Integer page){
        Specification<Product> specification = Specification.where(null);
        if (minCost != null) {
            specification = specification.and(ProductSpecification.priceGreaterThanOrEqualsTo(minCost));
        }
        if (maxCost != null) {
            specification = specification.and(ProductSpecification.priceLessThanOrEqualsTo(maxCost));
        }
        if (partTitle != null) {
            specification = specification.and(ProductSpecification.titleLike(partTitle));
        }
        return productRepository.findAll(specification, PageRequest.of(page - 1, 10));
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

//    public List<Product> findProductsWithSorting(String field){
//        return productRepository.findAll(Sort.by(Sort.Direction.ASC, field));
//    }
}
