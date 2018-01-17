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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin/contact", method = RequestMethod.GET)
    public ModelAndView newContact() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Contact contact = new Contact();
        modelAndView.addObject("userName", "User name: " + user.getName()
                + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("contact", contact);
        modelAndView.addObject("userId",  "User id: " + user.getId());
        modelAndView.setViewName("admin/contact");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/contact", method = RequestMethod.POST)
    public ModelAndView createNewContact(@Valid Contact contact, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Contact contactExists = contactService.findByName(contact.getName());
        if (contactExists != null) {
            bindingResult
                    .rejectValue("name", "error.contact",
                            "Contact with this name is exist, please try again with another name :)");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("/admin/contact");
        } else {
            contactService.saveContact(contact, user);
            modelAndView.addObject("contact", new Contact());
            modelAndView.setViewName("redirect:/admin/home");

        }
        return modelAndView;
    }

    @RequestMapping(value = "/admin/delete/{id}")
    public String delete(@PathVariable Integer id) {
        contactService.deleteById(id);
        return "redirect:/admin/home";
    }

    @RequestMapping(value = "/admin/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editContact(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Contact contact = contactService.findById(id);
        modelAndView.addObject("userName", "User name: " + user.getName()
                + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("contact", contact);
        modelAndView.setViewName("/admin/edit");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/edit/{id}", method = RequestMethod.POST)
    public ModelAndView updateContacts(@Valid Contact contact, BindingResult bindingResult, @PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
//        Contact contactExists = contactService.findByName(contact.getName());
//        if (contact == contactExists & contactExists != null) {
//            bindingResult
//                    .rejectValue("name", "error.contact",
//                            "Contact with this name is exist, please try again with another name :)");//       }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("/admin/edit");
        } else {
            contactService.updateContact(contact);
            modelAndView.setViewName("redirect:/admin/home");

        }
        return modelAndView;
    }


}