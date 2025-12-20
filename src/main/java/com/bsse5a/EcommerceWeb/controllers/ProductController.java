package com.bsse5a.EcommerceWeb.controllers;

import com.bsse5a.EcommerceWeb.dtos.ProductDto;
import com.bsse5a.EcommerceWeb.models.Product;
import com.bsse5a.EcommerceWeb.models.enums.GymEquipmentCategories;
import com.bsse5a.EcommerceWeb.security.CurrentUserDetails;
import com.bsse5a.EcommerceWeb.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/admin/dashboard/products")
    public String getProductsDashboard(Model model){
        List<ProductDto> products = productService.showAllProducts();
        model.addAttribute("products", products);
        return "product-dashboard";
    }

    @GetMapping("/products")
    public String showProducts(Model model){
        return "all-products";
    }

    @GetMapping("/admin/dashboard/create-products")
    public String showCreateProducts(Model model){
        model.addAttribute("product", new ProductDto());
        model.addAttribute("categories", GymEquipmentCategories.values());
        return "create-product";
    }

    @PostMapping("/admin/dashboard/create-products")
    public String createProducts(
            @Valid @ModelAttribute("product") ProductDto productDto,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes){

        System.out.println("=== POST Request Received ===");
        System.out.println("Product DTO: " + productDto);
        System.out.println("Has Errors: " + bindingResult.hasErrors());

        if(bindingResult.hasErrors()){
            System.out.println("=== Validation Errors ===");
            for (FieldError error : bindingResult.getFieldErrors()) {
                System.out.println("Field: " + error.getField() +
                        " - Error: " + error.getDefaultMessage() +
                        " - Rejected Value: " + error.getRejectedValue());
            }
            model.addAttribute("categories", GymEquipmentCategories.values());
            model.addAttribute("errorMessage", "Please fix the validation errors below.");
            return "create-product";
        }

        try{
            System.out.println("=== Attempting to save product ===");
            productService.createProduct(productDto);
            System.out.println("=== Product saved successfully ===");
            redirectAttributes.addFlashAttribute("successMessage", "Product created successfully!");
            return "redirect:/admin/dashboard/products";
        } catch (Exception e) {
            System.out.println("=== Error creating product ===");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("categories", GymEquipmentCategories.values());
            model.addAttribute("errorMessage", "Failed to create product: " + e.getMessage());
            return "create-product";
        }
    }

    @GetMapping("/admin/dashboard/products/delete/{id}")
    public String showDeleteConfirmation(@PathVariable Long id,
                                         Model model,
                                         RedirectAttributes redirectAttributes
                                         ){
        try{
            ProductDto productDto = productService.getProductById(id);
            if(productDto == null){
                redirectAttributes.addFlashAttribute("error","Product not found");
                return "redirect:/admin/dashboard/products";
            }
            model.addAttribute("productName", productDto.getTitle());
            model.addAttribute("productId", productDto.getId());
            return "product-delete";
        }catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error loading product: " + e.getMessage());
            return "redirect:/products";
        }
    }

    @PostMapping("/admin/dashboard/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id){

            productService.deleteProduct(id);
     return "redirect:/admin/dashboard/products";
    }

    @GetMapping("/admin/dashboard/products/edit/{id}")
    public String showEditProductForm(@PathVariable Long id,
                                      Model model,
                                      RedirectAttributes redirectAttributes) {
        try {
            ProductDto productDto = productService.getProductById(id);

            if (productDto == null) {
                redirectAttributes.addFlashAttribute("errorMessage", "Product not found!");
                return "redirect:/admin/dashboard/products";
            }

            model.addAttribute("product", productDto);
            model.addAttribute("categories", GymEquipmentCategories.values());

            return "product-update";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error: " + e.getMessage());
            return "redirect:/admin/dashboard/products";
        }
    }

    @PostMapping("/admin/dashboard/products/update/{id}")
    public String updateProduct(@PathVariable Long id,
                                @Valid @ModelAttribute("product") ProductDto productDto, // Use DTO here
                                BindingResult bindingResult,
                                Model model,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", GymEquipmentCategories.values());
            return "product-update";
        }

        try {
            productDto.setId(id); // Ensure the ID from URL is set in DTO
            productService.updateProduct(productDto); // Pass DTO to service

            redirectAttributes.addFlashAttribute("successMessage", "Product updated successfully!");
            return "redirect:/admin/dashboard/products";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to update: " + e.getMessage());
            model.addAttribute("categories", GymEquipmentCategories.values());
            return "product-update";
        }
    }

}
