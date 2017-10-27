package com.gmail.psse69.repository;

import com.gmail.psse69.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    List<Contact> findAll();

    Contact findContactByName(String name);


}
