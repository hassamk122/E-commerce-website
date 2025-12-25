package com.bsse5a.EcommerceWeb.respositories;

import com.bsse5a.EcommerceWeb.models.Product;
import com.bsse5a.EcommerceWeb.models.enums.GymEquipmentCategories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByGymEquipmentCategories(GymEquipmentCategories category);
}
