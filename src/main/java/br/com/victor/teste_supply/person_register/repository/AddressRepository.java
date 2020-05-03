package br.com.victor.teste_supply.person_register.repository;

import br.com.victor.teste_supply.person_register.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
