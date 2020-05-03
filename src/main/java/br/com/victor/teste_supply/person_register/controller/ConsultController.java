package br.com.victor.teste_supply.person_register.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

public interface ConsultController <T>{
    JpaRepository<T, Long> getRepository();

    @GetMapping
    default ResponseEntity<Page<T>> findAll(Pageable pageable){
        return ResponseEntity.ok(getRepository().findAll(pageable));
    }

    @GetMapping("/{id}")
    default ResponseEntity<T> findById(@PathVariable("id") Long id){
        Optional<T> t = getRepository().findById(id);
        if(t.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(t.get());
    }

}
