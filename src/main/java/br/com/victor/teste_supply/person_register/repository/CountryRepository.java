package br.com.victor.teste_supply.person_register.repository;

import br.com.victor.teste_supply.person_register.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
