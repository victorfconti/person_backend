package br.com.victor.teste_supply.person_register.service;

import br.com.victor.teste_supply.person_register.model.Address;
import br.com.victor.teste_supply.person_register.model.Person;
import br.com.victor.teste_supply.person_register.model.dto.PersonDTO;
import br.com.victor.teste_supply.person_register.model.exceptions.NotFoundException;
import br.com.victor.teste_supply.person_register.repository.AddressRepository;
import br.com.victor.teste_supply.person_register.repository.CityRepository;
import br.com.victor.teste_supply.person_register.repository.ContactRepository;
import br.com.victor.teste_supply.person_register.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;
    private final AddressRepository addressRepository;
    private final ContactRepository contactRepository;
    private final CityRepository cityRepository;

    public PersonService(PersonRepository personRepository, AddressRepository addressRepository,
                         ContactRepository contactRepository, CityRepository cityRepository){
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
        this.contactRepository = contactRepository;
        this.cityRepository = cityRepository;
        modelMapper = new ModelMapper();
    }

    private void saveAddress(Address address){
        var cityId = address.getCity().getId();
        var city = cityRepository.findById(cityId);
        if(city.isEmpty()){
            throw new NotFoundException("City not found");
        }
        address.setCity(city.get());
        addressRepository.saveAndFlush(address);

    }


    @Transactional
    public Person save(PersonDTO personDTO, Long domainId) {
        Person person = modelMapper.map(personDTO, Person.class);
        saveAddress(person.getAddress());
        contactRepository.saveAll(person.getContacts());
        contactRepository.flush();
        person.setDomainId(domainId);
        return personRepository.saveAndFlush(person);
    }

    @Transactional
    public Person update(PersonDTO personDTO, Long id, Long domainId) {
        if(personRepository.existsByIdAndDomainId(id, domainId)){
            throw new NotFoundException("Person not found");
        }
        Person person = modelMapper.map(personDTO, Person.class);
        person.setId(id);
        person.setDomainId(domainId);
        saveAddress(person.getAddress());
        contactRepository.saveAll(person.getContacts());
        contactRepository.flush();
        return personRepository.saveAndFlush(person);
    }

    @Transactional
    public Person delete(Long id, Long domainId) {
        Optional<Person> person = personRepository.findByIdAndDomainId(id, domainId);
        if(person.isEmpty()){
            throw new NotFoundException("Person not Found");
        }
        if (person.get().getAddress() != null){
            addressRepository.delete(person.get().getAddress());
        }
        if(person.get().getContacts() != null && person.get().getContacts().isEmpty()){
            contactRepository.deleteAll(person.get().getContacts());
        }
        personRepository.delete(person.get());
        return person.get();
    }
}
