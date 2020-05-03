package br.com.victor.teste_supply.person_register.model;

import br.com.victor.teste_supply.person_register.model.type.ContactType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private ContactType contactType;
    @NotNull
    private String value;
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private Person person;
}
