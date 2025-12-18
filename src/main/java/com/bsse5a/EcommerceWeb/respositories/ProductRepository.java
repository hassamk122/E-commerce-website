package com.bsse5a.EcommerceWeb.respositories;

import com.bsse5a.EcommerceWeb.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
