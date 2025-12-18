package com.bsse5a.EcommerceWeb.services;

import com.bsse5a.EcommerceWeb.dtos.ProductDto;
import com.bsse5a.EcommerceWeb.models.Product;
import com.bsse5a.EcommerceWeb.respositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void createProduct(ProductDto productDto){
        if(productDto == null) return;
        Product product = Product.builder()
                .title(productDto.getTitle())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .gymEquipmentCategories(productDto.getGymEquipmentCategories())
                .imageUrl(productDto.getImageUrl())
                .quantity(productDto.getQuantity())
                .build();
        productRepository.save(product);
    }
}
