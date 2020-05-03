package br.com.victor.teste_supply.person_register.service;

import br.com.victor.teste_supply.person_register.model.Contact;
import br.com.victor.teste_supply.person_register.model.dto.ContactDTO;
import br.com.victor.teste_supply.person_register.model.exceptions.NotFoundException;
import br.com.victor.teste_supply.person_register.repository.ContactRepository;
import br.com.victor.teste_supply.person_register.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
public class ContactService {
    private final ContactRepository contactRepository;
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    public ContactService(ContactRepository contactRepository, PersonRepository personRepository){
        this.contactRepository = contactRepository;
        this.personRepository = personRepository;
        this.modelMapper = new ModelMapper();
    }

    @Transactional
    public Contact save(ContactDTO contact, Long personId, Long domainId) {
        var person = personRepository.findByIdAndDomainId(personId, domainId);
        if(person.isEmpty()){
            throw new NotFoundException("Person not found");
        }
        var contactTypesAlreadyHave = person.get().getContacts().stream().map(Contact::getContactType).
                collect(Collectors.toList());
        if(contactTypesAlreadyHave.contains(contact.getContactType())){
            throw new IllegalArgumentException("Already have contact type");
        }
        var contactToSave = modelMapper.map(contact, Contact.class);
        contactToSave.setPerson(person.get());
        return contactRepository.saveAndFlush(contactToSave);
    }

    @Transactional
    public Contact update(ContactDTO contact, Long id, Long domainId) {
        var contactToUpdate = contactRepository.findByIdAndPersonDomainId(id, domainId);
        if(contactToUpdate.isEmpty()){
            throw new NotFoundException("User not found");
        }
        contactToUpdate.get().setContactType(contact.getContactType());
        contactToUpdate.get().setValue(contact.getValue());
        return contactRepository.saveAndFlush(contactToUpdate.get());
    }
}
