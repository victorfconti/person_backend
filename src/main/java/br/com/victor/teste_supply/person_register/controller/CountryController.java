package br.com.victor.teste_supply.person_register.controller;

import br.com.victor.teste_supply.person_register.model.Country;
import br.com.victor.teste_supply.person_register.repository.CountryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/countries")
public class CountryController implements ConsultController<Country> {
    private final CountryRepository countryRepository;

    public CountryController(CountryRepository countryRepository){
        this.countryRepository = countryRepository;
    }

    @Override
    public JpaRepository<Country, Long> getRepository() {
        return countryRepository;
    }
}
