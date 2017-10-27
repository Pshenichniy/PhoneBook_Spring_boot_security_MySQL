package com.gmail.psse69.service;

import com.gmail.psse69.model.Contact;

import java.util.List;


public interface ContactService {

    public List<Contact> findAll();
    public void saveContact(Contact contact);
    public Contact findByName(String name);

}
