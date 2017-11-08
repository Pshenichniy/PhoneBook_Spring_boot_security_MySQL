package com.gmail.psse69.service;

import com.gmail.psse69.model.Contact;
import com.gmail.psse69.model.User;
import com.gmail.psse69.repository.ContactRepository;
import com.gmail.psse69.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;


@Service
@Transactional
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;


    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }


    public List<Contact> findByUser(User user) {
        return contactRepository.findByUser(user);
    }


    @Override
    public void saveContact(Contact contact, User user) {
        contact.setUser(user);
        contactRepository.save(contact);
    }

    @Override
    public Contact findById(Integer id) {
       return contactRepository.findById(id);
    }


    @Override
    public Contact findByName(String name) {
       return contactRepository.findContactByName(name);
    }

    @Override
    @ModelAttribute
    public void updateContact(Contact contact) {
        Contact updateC = contactRepository.findById(contact.getId());
        if (updateC != null) {
            updateC.setName(contact.getName());
            updateC.setCompany(contact.getCompany());
            updateC.setPhone(contact.getPhone());
            updateC.setHomePhone(contact.getHomePhone());
            updateC.setEmail(contact.getEmail());
        }
    }

    @Override
    public void deleteById(Integer id) {
         contactRepository.deleteById(id);
    }

}
