package com.bsse5a.EcommerceWeb.controllers;

import com.bsse5a.EcommerceWeb.Security.CurrentUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String pageOnLoad(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated()
                && !(auth.getPrincipal() instanceof String && auth.getPrincipal().equals("anonymousUser"))) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String getHome(@AuthenticationPrincipal CurrentUserDetails currentUserDetails, Model model){
        model.addAttribute("name", currentUserDetails.getName());
        model.addAttribute("email", currentUserDetails.getEmail());
        return "Homepage";
    }
}
