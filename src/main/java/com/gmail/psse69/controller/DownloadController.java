package com.gmail.psse69.controller;

import com.gmail.psse69.model.Contact;
import com.gmail.psse69.model.User;
import com.gmail.psse69.service.ContactService;
import com.gmail.psse69.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/download")
public class DownloadController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;


    @RequestMapping(value = "/json", method = RequestMethod.GET)
    public void convertJSON(HttpServletResponse response) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        List<Contact> contact = contactService.findByUser(user);

//        Map<Integer, String> result1 = contact.stream().collect(
//                Collectors.toMap(Contact::getId, Contact::getPhone));
//        String result1 = Arrays.toString(contact.toArray());

        String jsonContact = new Gson().toJson(contact.toString());

        response.setHeader("Content-disposition", "attachment; filename= contacts.json");
        response.setContentType("application/force-download");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(jsonContact);
    }


    @RequestMapping(value = "/xml", method = RequestMethod.GET)
    public void convertXML(HttpServletResponse response) throws JAXBException, IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        List<Contact> contact = contactService.findByUser(user);

        JAXBContext jaxbContext = JAXBContext.newInstance(Contact.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(contact, new File("contacts.xml"));



        response.setHeader("Content-disposition", "attachment; filename= contacts.xml");
        response.setContentType("application/force-download");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(jaxbMarshaller);
    }
}