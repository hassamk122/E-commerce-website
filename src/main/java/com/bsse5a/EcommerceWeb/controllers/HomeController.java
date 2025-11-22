package com.bsse5a.EcommerceWeb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showIndex(){
        return "index";
    }

    @GetMapping("/home")
    public String showHome(){
        return "redirect:/";
    }
}
