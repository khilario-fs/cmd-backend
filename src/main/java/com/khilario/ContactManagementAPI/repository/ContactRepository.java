package com.khilario.ContactManagementAPI.repository;

import com.khilario.ContactManagementAPI.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByNameContainingIgnoreCase(String name);
}
