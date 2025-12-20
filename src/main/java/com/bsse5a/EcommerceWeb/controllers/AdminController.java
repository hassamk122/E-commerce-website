package com.bsse5a.EcommerceWeb.controllers;


import com.bsse5a.EcommerceWeb.security.CurrentUserDetails;
import com.bsse5a.EcommerceWeb.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Autowired
    private ProductService productService;

    @GetMapping("/admin/dashboard")
    public String getAdminDashboard(Model model){
        model.addAttribute("productsCount",productService.allProductsCount());
        return "admin-dashboard";
    }
}
