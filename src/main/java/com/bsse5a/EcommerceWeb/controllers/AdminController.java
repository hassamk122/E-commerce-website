package com.bsse5a.EcommerceWeb.controllers;


import com.bsse5a.EcommerceWeb.security.CurrentUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/dashboard")
    public String getAdminDashboard(@AuthenticationPrincipal CurrentUserDetails currentUserDetails, Model model){
        if (currentUserDetails != null) {
                model.addAttribute("username", currentUserDetails.getName());
                model.addAttribute("email", currentUserDetails.getEmail());
        }
        return "admin-dashboard";
    }
}
