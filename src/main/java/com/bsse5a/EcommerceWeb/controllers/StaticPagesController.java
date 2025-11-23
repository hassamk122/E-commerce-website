package com.bsse5a.EcommerceWeb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticPagesController {

    @GetMapping("/aboutus")
    public String showAboutUs(){
        return "about-us";
    }
    @GetMapping("/contactus")
    public String showContactUs(){
        return "contact-us";
    }

}
