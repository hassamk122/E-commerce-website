package com.bsse5a.EcommerceWeb.controllers;

import com.bsse5a.EcommerceWeb.Security.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String pageOnLoad(){
        return "index";
    }

    @GetMapping("/home")
    public String getHome(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model){
        model.addAttribute("name", customUserDetails.getName());
        model.addAttribute("email", customUserDetails.getEmail());
        return "Homepage";
    }
}
