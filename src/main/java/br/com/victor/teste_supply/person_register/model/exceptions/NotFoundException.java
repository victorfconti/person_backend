package br.com.victor.teste_supply.person_register.model.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String personNotFound) {
        super(personNotFound);
    }
}
