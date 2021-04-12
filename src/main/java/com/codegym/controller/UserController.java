package com.codegym.controller;

import java.security.Principal;

import com.codegym.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private IProductService productService;

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("/index");
    }

    @GetMapping("/user")
    public ModelAndView user(Principal principal) {
        // Get authenticated user name from Principal
        ModelAndView modelAndView = new ModelAndView("/user");
        modelAndView.addObject("products", productService.findAll());
        System.out.println(principal.getName());
        return modelAndView;

    }

    @GetMapping("/admin")
    public ModelAndView admin() {
        // Get authenticated user name from SecurityContext
        SecurityContext context = SecurityContextHolder.getContext();
        System.out.println(context.getAuthentication().getName());
        ModelAndView modelAndView = new ModelAndView("/admin");
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }
}