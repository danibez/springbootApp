package com.example.demo.service;

import com.example.demo.exception.category.CategoryNotFoundException;
import com.example.demo.exception.category.InvalidCategoryNameException;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Category create(Category category) {
        if (category.getName() == null || category.getName().trim().isEmpty()) {
            throw new InvalidCategoryNameException("Nome da categoria não pode ser vazio.");
        }
        return repository.save(category);
    }

    public List<Category> findAll() { return repository.findAll(); }

    public Category findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Categoria não encontrada com id: " + id));
    }

    public Category update(Long id, Category category) {
        findById(id);
        category.setId(id);
        return repository.save(category);
    }

    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}