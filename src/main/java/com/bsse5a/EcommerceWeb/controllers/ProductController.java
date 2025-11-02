package com.bsse5a.EcommerceWeb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
    @GetMapping("/admin/dashboard/products")
    public String getProductsDashboard(){
        return "product-dashboard";
    }
}
