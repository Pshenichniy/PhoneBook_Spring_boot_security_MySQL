package com.gmail.psse69.repositories;

import com.gmail.psse69.configuration.RepositoryConfiguration;
import com.gmail.psse69.model.Contact;
import com.gmail.psse69.model.User;
import com.gmail.psse69.repository.ContactRepository;
import com.gmail.psse69.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {RepositoryConfiguration.class})
public class ContactRepositoryTest {

    //User user;

   // Contact contact;


    @Autowired
    ContactRepository contactRepository;

    @Autowired
    UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }




    public User addUser() {
        User user = new User();
        user.setActive(1);
        user.setName("Jin");
        user.setLastName("Kill");
        user.setEmail("jin@test.com");
        user.setPassword("123456");
        assertNull(user.getId());
        userRepository.save(user);
        return user;
    }


    @Test
    public void addContact() {
        Contact contact = new Contact();
        contact.setName("TestFromTest");
        contact.setCompany("TestFromTest");
        contact.setPhone("+38(099)123-12-12");
        contact.setHomePhone("(044)123-12-12");
        contact.setEmail("testfromtest@test.com");
        contact.setUser(addUser());

        assertNull(contact.getId());
        contactRepository.save(contact);
    }
}
