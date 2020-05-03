package br.com.victor.teste_supply.person_register.repository;

import br.com.victor.teste_supply.person_register.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
