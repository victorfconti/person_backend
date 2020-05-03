package br.com.victor.teste_supply.person_register.repository;

import br.com.victor.teste_supply.person_register.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long> {
}
