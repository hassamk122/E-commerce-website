package com.bsse5a.EcommerceWeb.globals;

import com.bsse5a.EcommerceWeb.services.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalCartAdvice {

    @Autowired
    private CartService cartService;

    /**
     * This method adds cartItemCount to EVERY page's model automatically
     * So it's available in index-layout.html and all other templates
     */
    @ModelAttribute("cartItemCount")
    public int addCartCountToModel(HttpSession session) {
        try {
            return cartService.getCartItemCount(session);
        } catch (Exception e) {
            return 0; // Return 0 if any error or user not logged in
        }
    }
}