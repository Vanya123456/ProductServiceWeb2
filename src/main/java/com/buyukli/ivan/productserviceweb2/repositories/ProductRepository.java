package com.buyukli.ivan.productserviceweb2.repositories;

import com.buyukli.ivan.productserviceweb2.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductByCostBetween(String min, String max);
}
