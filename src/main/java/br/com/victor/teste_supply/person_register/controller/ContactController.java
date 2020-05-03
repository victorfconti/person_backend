package br.com.victor.teste_supply.person_register.controller;

import br.com.victor.teste_supply.person_register.model.Contact;
import br.com.victor.teste_supply.person_register.model.dto.ContactDTO;
import br.com.victor.teste_supply.person_register.model.exceptions.NotFoundException;
import br.com.victor.teste_supply.person_register.repository.ContactRepository;
import br.com.victor.teste_supply.person_register.service.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    private final ContactService contactService;
    private final ContactRepository contactRepository;

    public ContactController(ContactService contactService, ContactRepository contactRepository){
        this.contactService = contactService;
        this.contactRepository = contactRepository;
    }

    @GetMapping("/person/{personId}")
    public ResponseEntity<List<Contact>> findPerson(@PathVariable Long personId, @RequestHeader("domain_id")Long domainId){
        var contacts = contactRepository.findByPersonIdAndPersonDomainId(personId, domainId);
        if(contacts == null || contacts.isEmpty()){
            throw new NotFoundException("Contact not found");
        }
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> find(@PathVariable Long id, @RequestHeader("domain_id")Long domainId){
        return ResponseEntity.ok(findContact(id, domainId));
    }

    @PostMapping("/person/{personId}")
    public ResponseEntity<Contact> post(@RequestBody ContactDTO contact, @PathVariable("personId") Long personId,
                                        @RequestHeader("domain_id")Long domainId){
        var contactSave = contactService.save(contact, personId, domainId);
        return ResponseEntity.ok(contactSave);
    }

    private Contact findContact(Long id, Long domainId){
        var contact =  contactRepository.findByIdAndPersonDomainId(id, domainId);
        if(contact.isEmpty()){
            throw new NotFoundException("Contact not found");
        }
        return contact.get();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Contact> delete(@PathVariable("id") Long id, @RequestHeader("domain_id") Long domainId){
        var contact = findContact(id, domainId);
        contactRepository.delete(contact);
        return ResponseEntity.ok(contact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact>  put(@RequestBody ContactDTO contact, @PathVariable("id") Long id,
                                        @RequestHeader("domain_id") Long domainId){
        var contactUpdated = contactService.update(contact, id, domainId);
        return ResponseEntity.ok(contactUpdated);
    }

}
