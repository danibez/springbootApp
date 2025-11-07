package com.example.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServicoModel {
    
    @Id
    @GeneratedValue
    long id;
    String nome;
    double valor;

    @ManyToMany
    @JoinTable(
        name="servico_pet",
        joinColumns=
            @JoinColumn(name="servico_id", referencedColumnName="id"),
        inverseJoinColumns=
            @JoinColumn(name="pet_id", referencedColumnName="id")
    )
    List<PetModel> pets;

}
