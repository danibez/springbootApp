package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PetModel;
import com.example.demo.model.UserModel;

@Repository
public interface PetRepository extends JpaRepository<PetModel, Long> {

    List<PetModel> findByDono(UserModel dono);
}
