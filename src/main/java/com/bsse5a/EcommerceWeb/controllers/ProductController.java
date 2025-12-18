package com.bsse5a.EcommerceWeb.controllers;

import com.bsse5a.EcommerceWeb.dtos.ProductDto;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/admin/dashboard/products")
    public String getProductsDashboard(@AuthenticationPrincipal CurrentUserDetails currentUserDetails, Model model){
        if(currentUserDetails != null){
            model.addAttribute("name", currentUserDetails.getName());
            model.addAttribute("email", currentUserDetails.getEmail());
        }
        return "product-dashboard";
    }

    @GetMapping("/products")
    public String showProducts(){
        return "all-products";
    }

    @GetMapping("/admin/dashboard/create-products")
    public String showCreateProducts(@AuthenticationPrincipal CurrentUserDetails currentUserDetails,
                                     Model model){
        if(currentUserDetails != null){
            model.addAttribute("name", currentUserDetails.getName());
            model.addAttribute("email", currentUserDetails.getEmail());
        }
        model.addAttribute("product", new ProductDto());
        model.addAttribute("categories", GymEquipmentCategories.values());
        return "create-product";
    }

    @PostMapping("/admin/dashboard/create-products")
    public String createProducts(
            @AuthenticationPrincipal CurrentUserDetails currentUserDetails,
            @Valid @ModelAttribute("product") ProductDto productDto,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes){

        // Add user details
        if(currentUserDetails != null){
            model.addAttribute("name", currentUserDetails.getName());
            model.addAttribute("email", currentUserDetails.getEmail());
        }

        // DEBUG: Log the received data
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
}
