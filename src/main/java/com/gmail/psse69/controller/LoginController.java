package com.gmail.psse69.controller;

import com.gmail.psse69.model.Contact;
import com.gmail.psse69.model.User;
import com.gmail.psse69.service.ContactService;
import com.gmail.psse69.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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


    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "User with this name is exist, please try again with another name :)");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }

    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
         List<Contact> contact = contactService.findAll();
        modelAndView.addObject("userName", "Admin name: " + user.getName()
                + " " + user.getLastName() + " (" + user.getEmail() + ")");
        //modelAndView.addObject("adminMessage","Phone book");
        modelAndView.addObject("contactList", contact);
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

    @RequestMapping(value="/admin/contact", method = RequestMethod.GET)
    public ModelAndView newContact(){
        ModelAndView modelAndView = new ModelAndView();
        Contact contact = new Contact();
        modelAndView.addObject("contact", contact);
        modelAndView.setViewName("admin/contact");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/contact", method = RequestMethod.POST)
    public ModelAndView createNewContact(@Valid Contact contact, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Contact contactExists = contactService.findByName(contact.getName());
        if (contactExists != null) {
            bindingResult
                    .rejectValue("name", "error.contact",
                            "Contact with this name is exist, please try again with another name :)");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("/admin/contact");
        } else {
            contactService.saveContact(contact);
            modelAndView.addObject("successMessage", "Contact registered successfully");
            modelAndView.addObject("contact", new Contact());
            modelAndView.setViewName("/admin/contact");

        }
        return modelAndView;
    }



}
