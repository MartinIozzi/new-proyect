package com.dualproject.ecommerce.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Routes {
    
    @GetMapping(value = {"","/","/home"})
    public String getHome(Model model, Authentication authentication){
        return "index";
    }

}
