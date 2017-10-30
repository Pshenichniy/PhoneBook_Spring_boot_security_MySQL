package com.gmail.psse69.service;

import com.gmail.psse69.model.Contact;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;


public interface ContactService {

    List<Contact> findAll();
    void saveContact(Contact contact);
    Contact findById(Integer id);
    void deleteById(Integer id);
    Contact findByName(String name);
    void updateContact(Contact contact);

}
