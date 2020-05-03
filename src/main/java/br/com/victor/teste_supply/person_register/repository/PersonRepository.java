package br.com.victor.teste_supply.person_register.repository;

import br.com.victor.teste_supply.person_register.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByIdAndDomainId(Long id, Long domainId);
    Page<Person> findAllByDomainId(Long domainId, Pageable pageable);
    boolean existsByIdAndDomainId(Long id, Long domainId);
}
