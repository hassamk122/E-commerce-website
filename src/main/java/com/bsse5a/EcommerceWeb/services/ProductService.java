package com.bsse5a.EcommerceWeb.services;

import com.bsse5a.EcommerceWeb.dtos.ProductDto;
import com.bsse5a.EcommerceWeb.models.Product;
import com.bsse5a.EcommerceWeb.respositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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

    public Long allProductsCount(){
        return productRepository.count();
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

    public ProductDto getProductById(Long id){
        Optional<Product> product = productRepository.findById(id);
        ProductDto dto = ProductDto.builder()
                .id(product.get().getId())
                .title(product.get().getTitle())
                .description(product.get().getDescription())
                .price(product.get().getPrice())
                .gymEquipmentCategories(product.get().getGymEquipmentCategories())
                .imageUrl(product.get().getImageUrl())
                .quantity(product.get().getQuantity())
                .build();
        return dto;
    }


    public void deleteProduct(Long id){
        if(!productRepository.existsById(id)){
            System.out.println("Product not found by id"+id);
        }
        productRepository.deleteById(id);
    }


    public Product updateProduct(ProductDto productDto) {
        Product existingProduct = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productDto.getId()));

        existingProduct.setTitle(productDto.getTitle());
        existingProduct.setDescription(productDto.getDescription());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setQuantity(productDto.getQuantity());
        existingProduct.setGymEquipmentCategories(productDto.getGymEquipmentCategories());
        existingProduct.setImageUrl(productDto.getImageUrl());

        existingProduct.setUpdatedAt(LocalDate.now());

        return productRepository.save(existingProduct);
    }
}
