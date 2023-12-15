package com.khilario.ContactManagementAPI.controller;

import com.khilario.ContactManagementAPI.exception.ResourceNotFoundException;
import com.khilario.ContactManagementAPI.model.Contact;
import com.khilario.ContactManagementAPI.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RestController
@RequestMapping("contacts")
public class ContactController {
    @Autowired
    private ContactRepository contactRepository;

    // get all contacts with optional request parameter for name
    @GetMapping()
    public List<Contact> getAllContacts(@RequestParam(required = false) String name) {
        if (name != null)
            return contactRepository.findByNameContainingIgnoreCase(name);
        return contactRepository.findAll();
    }

    // create contact rest api
    @PostMapping()
    public Contact createContact(@RequestBody Contact contact) {
        return contactRepository.save(contact);
    }

    // get contact by id rest api
    @GetMapping("/{id}")
    public Contact getContactById(@PathVariable Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not exist with id :" + id));
    }

    // update contact rest api
    @PutMapping("/{id}")
    public Contact updateContact(@PathVariable Long id, @RequestBody Contact contactDetails) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not exist with id :" + id));

        contact.setName(contactDetails.getName());
        contact.setEmail(contactDetails.getEmail());
        contact.setAddress(contactDetails.getAddress());
        contact.setContactNo(contactDetails.getContactNo());

        return contactRepository.save(contact);
    }

    // delete contact rest api
    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id) {
        contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not exist with id :" + id));
        contactRepository.deleteById(id);
    }
}
