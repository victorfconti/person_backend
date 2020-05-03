package br.com.victor.teste_supply.person_register.controller;

import br.com.victor.teste_supply.person_register.model.City;
import br.com.victor.teste_supply.person_register.repository.CityRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cities")
public class CityController implements ConsultController<City> {
    private final CityRepository cityRepository;

    public CityController(CityRepository cityRepository){
        this.cityRepository = cityRepository;
    }

    @Override
    public JpaRepository<City, Long> getRepository() {
        return cityRepository;
    }
}
