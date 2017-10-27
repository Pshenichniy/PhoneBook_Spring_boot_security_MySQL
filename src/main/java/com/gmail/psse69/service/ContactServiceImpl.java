package com.gmail.psse69.service;

import com.gmail.psse69.model.Contact;
import com.gmail.psse69.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("contactService")
@Transactional
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }


    @Override
    public void saveContact(Contact contact) {
        contact.setName(contact.getName());
        contact.setCompany(contact.getCompany());
        contact.setEmail(contact.getEmail());
        contact.setPhone(contact.getPhone());
        contact.setHomePhone(contact.getHomePhone());
        contactRepository.save(contact);
    }

    @Override
    public Contact findByName(String name) {
        return contactRepository.findContactByName(name);
    }
}
