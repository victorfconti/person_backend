package br.com.victor.teste_supply.person_register.model.dto;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.victor.teste_supply.person_register.model.type.ContactType;
import lombok.Getter;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PersonDTO {
    private String name;
    private LocalDate birthday;
    private AddressDTO address;
    private List<ContactDTO> contacts;
    private String cpf;

    @SuppressWarnings("unused")
    public PersonDTO(String name, LocalDate birthday, AddressDTO address, List<ContactDTO> contacts, String cpf) {
        setName(name);
        setBirthday(birthday);
        setAddressId(address);
        setContacts(contacts);
        setCpf(cpf);
    }

    public void setName(String name) {
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("Name is empty");
        }
        this.name = name;
    }


    public void setBirthday(LocalDate birthday) {
        if(birthday == null){
            throw new IllegalArgumentException("Empty birthday");
        }
        if (Period.between(birthday, LocalDate.now()).getYears() < 18){
            throw new IllegalArgumentException("Underage");
        }
        this.birthday = birthday;
    }

    public void setAddressId(AddressDTO address) {
        if(address == null){
            throw new IllegalArgumentException("Empty address");
        }
        this.address = address;
    }

    private boolean verifyDuplicatedContactType(List<ContactDTO> contacts){
        var types = contacts.stream().map(ContactDTO::getContactType).collect(Collectors.toList());
        return Collections.frequency(types, ContactType.EMAIL) > 1 ||
               Collections.frequency(types, ContactType.SKYPE) > 1 ||
               Collections.frequency(types, ContactType.TELEPHONE) > 1;

    }

    public void setContacts(List<ContactDTO> contacts){
        if(contacts == null || contacts.isEmpty()){
            throw new IllegalArgumentException("Empty contacts");
        }
        if(contacts.size() > 3){
            throw new IllegalArgumentException("Repeated contact");
        }
        if(verifyDuplicatedContactType(contacts)){
            throw new IllegalArgumentException("Duplicated value");
        }
        this.contacts = contacts;
    }

    public void setCpf(String cpf) {
        new CPFValidator().assertValid(cpf.replace(".", "").replace("-", "").
                replace(" ", ""));
        this.cpf = cpf;
    }
}
