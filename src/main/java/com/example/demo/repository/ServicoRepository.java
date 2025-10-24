package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ServicoModel;

@Repository
public interface ServicoRepository extends JpaRepository<ServicoModel, Long> {
    
}