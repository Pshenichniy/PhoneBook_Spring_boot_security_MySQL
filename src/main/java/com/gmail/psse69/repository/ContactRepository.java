package com.gmail.psse69.repository;

import com.gmail.psse69.model.Contact;
import com.gmail.psse69.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;


@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    List<Contact> findAll();
    List<Contact> findByUser(User user);
    Contact findContactByName(String name);
    void deleteById(Integer id);
    Contact findById(Integer id);


}
