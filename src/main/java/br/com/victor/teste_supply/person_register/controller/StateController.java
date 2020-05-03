package br.com.victor.teste_supply.person_register.controller;

import br.com.victor.teste_supply.person_register.model.State;
import br.com.victor.teste_supply.person_register.repository.StateRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/states/")
public class StateController implements ConsultController<State> {
    private final StateRepository stateRepository;

    public StateController(StateRepository stateRepository){
        this.stateRepository = stateRepository;
    }

    @Override
    public StateRepository getRepository() {
        return stateRepository;
    }
}
