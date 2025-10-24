package com.example.demo.model;

import java.util.List;

import org.hibernate.annotations.ValueGenerationType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PetModel {
    
    @Id
    @GeneratedValue
    long id;
    String name;

    @ManyToOne
    @JoinColumn(name = "dono_id")
    UserModel dono;

    @ManyToMany(mappedBy="pets")
    List<ServicoModel> servicos;

}
