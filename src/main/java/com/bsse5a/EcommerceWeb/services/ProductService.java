package com.bsse5a.EcommerceWeb.services;

import com.bsse5a.EcommerceWeb.dtos.ProductDto;
import com.bsse5a.EcommerceWeb.mappers.ProductMapper;
import com.bsse5a.EcommerceWeb.models.Product;
import com.bsse5a.EcommerceWeb.respositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper){
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }


    public void createProduct(ProductDto productDto){
        if(productDto == null) return;
        Product product = productMapper.toEntity(productDto);
        productRepository.save(product);
    }

    public Long allProductsCount(){
        return productRepository.count();
    }

    public List<ProductDto> showAllProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> {
                    ProductDto dto = productMapper.toDto(product);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public ProductDto getProductById(Long id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()) return null;
        return productMapper.toDto(product.orElse(null));
    }


    public void deleteProduct(Long id){
        if(!productRepository.existsById(id)){
            System.out.println("Product not found by id"+id);
        }
        productRepository.deleteById(id);
    }


    public Product updateProduct(ProductDto productDto) {
        if (productDto.getId() == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }

        Product existingProduct = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productDto.getId()));


        existingProduct.setTitle(productDto.getTitle());
        existingProduct.setDescription(productDto.getDescription());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setQuantity(productDto.getQuantity());
        existingProduct.setGymEquipmentCategories(productDto.getGymEquipmentCategories());

        if (productDto.getImageUrl() != null && !productDto.getImageUrl().trim().isEmpty()) {
            existingProduct.setImageUrl(productDto.getImageUrl());
        }

        existingProduct.setUpdatedAt(LocalDate.now());

        return productRepository.save(existingProduct);
    }
}
