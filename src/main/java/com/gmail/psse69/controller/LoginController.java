package com.gmail.psse69.controller;

import com.gmail.psse69.model.Contact;
import com.gmail.psse69.model.User;
import com.gmail.psse69.service.ContactService;
import com.gmail.psse69.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
         List<Contact> contact = contactService.findByUser(user);
        modelAndView.addObject("userName", "User name: " + user.getName()
                + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("userId",   user.getId());
        modelAndView.addObject("contactList", contact);
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

}
