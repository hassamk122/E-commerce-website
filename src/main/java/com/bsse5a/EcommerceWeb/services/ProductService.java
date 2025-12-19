package com.bsse5a.EcommerceWeb.services;

import com.bsse5a.EcommerceWeb.dtos.ProductDto;
import com.bsse5a.EcommerceWeb.models.Product;
import com.bsse5a.EcommerceWeb.respositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<ProductDto> showAllProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> {
                    ProductDto dto = ProductDto.builder()
                            .id(product.getId())
                            .title(product.getTitle())
                            .description(product.getDescription())
                            .price(product.getPrice())
                            .gymEquipmentCategories(product.getGymEquipmentCategories())
                            .imageUrl(product.getImageUrl())
                            .quantity(product.getQuantity())
                            .build();
                    return dto;
                })
                .collect(Collectors.toList());
    }

}
