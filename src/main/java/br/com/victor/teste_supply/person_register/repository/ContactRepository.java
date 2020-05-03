package br.com.victor.teste_supply.person_register.repository;

import br.com.victor.teste_supply.person_register.model.Contact;
import br.com.victor.teste_supply.person_register.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findAllByPerson(Person person);
    @Query("select p.domainId from Contact c join c.person p where c.id = :id")
    Long findDomainId(@Param("id") Long id);
//    @Query("select c from Contact  c join c.person p where c.id = :id and p.domainId = :domainId")
//    Optional<Contact> findByIdAndDomainId(@Param("id") Long id, @Param("domainId")Long domainId);
    Optional<Contact> findByIdAndPersonDomainId(Long id, Long domainId);
    List<Contact> findByPersonIdAndPersonDomainId(Long id, Long domainId);

}
