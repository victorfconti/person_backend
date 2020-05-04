package br.com.victor.teste_supply.person_register.controller;

import br.com.victor.teste_supply.person_register.model.Person;
import br.com.victor.teste_supply.person_register.model.dto.PersonDTO;
import br.com.victor.teste_supply.person_register.repository.PersonRepository;
import br.com.victor.teste_supply.person_register.service.PersonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/people")
public class PersonController {
    private final PersonRepository personRepository;
    private final PersonService personService;

    public PersonController(PersonRepository personRepository, PersonService personService){
        this.personRepository = personRepository;
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<Page<Person>> findAll(@RequestHeader("domain_id")Long domainId, Pageable pageable){
        return ResponseEntity.ok(personRepository.findAllByDomainId(domainId, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> get(@PathVariable("id")Long id, @RequestHeader("domain_id")Long domainId){
        Optional<Person> person = personRepository.findByIdAndDomainId(id, domainId);
        if (person.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(person.get());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Person> post(@RequestBody PersonDTO personDTO, @RequestHeader(value="domain_id", required = true) Long domainId){
        Person person = personService.save(personDTO, domainId);
        return ResponseEntity.created(URI.create("/people")).body(person);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Person> put(@RequestBody PersonDTO personDTO, @PathVariable Long id,
                                      @RequestHeader(value = "domain_id", required = true)Long domainId){
        Person person = personService.update(personDTO, id, domainId);
        return ResponseEntity.ok(person);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Person> delete(@PathVariable Long id, @RequestHeader(value="domain_id", required=true) Long domainId){
        Person person = personService.delete(id, domainId);
        return ResponseEntity.ok(person);
    }

}
