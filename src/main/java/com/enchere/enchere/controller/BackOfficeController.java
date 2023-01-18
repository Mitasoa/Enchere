package com.enchere.enchere.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.enchere.enchere.model.Admin;
import com.enchere.enchere.repository.BackOfficeRepository;

@Controller
public class BackOfficeController {
    @Autowired
    BackOfficeRepository backRep;

    @RequestMapping("/")
    public String Login(Model model, HttpServletRequest request) {
        Admin admin = new Admin();
        if (request.getParameter("pseudo") != null && request.getParameter("pseudo") != null) {
            admin.setPseudo(request.getParameter("pseudo"));
            admin.setMotDePasse(request.getParameter("pseudo"));
            ArrayList __admin = backRep.Login(admin);
            if (__admin.size() != 0) {

            } else {
                model.addAttribute("error", "Compte inexistant !");
            }
        }
        model.addAttribute("content", "login");
        model.addAttribute("contentpath", "View/login");
        return "View/login";
    }

}
