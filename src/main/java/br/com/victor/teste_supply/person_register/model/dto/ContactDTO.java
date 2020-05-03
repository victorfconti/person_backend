package br.com.victor.teste_supply.person_register.model.dto;

import br.com.victor.teste_supply.person_register.model.type.ContactType;
import lombok.Getter;

@Getter
public class ContactDTO {
        private ContactType contactType;
        private String value;

        public ContactDTO(ContactType contactType, String value){
                setContactType(contactType);
                setValue(value);
        }

        public void setContactType(ContactType contactType) {
                if(contactType == null){
                        throw new IllegalArgumentException("Contact type is empty");
                }
                this.contactType = contactType;
        }

        public void setValue(String value) {
                if(value == null || value.isEmpty() || value.isBlank()){
                        throw new IllegalArgumentException("The contact value is empty");
                }
                this.value = value;
        }
}

