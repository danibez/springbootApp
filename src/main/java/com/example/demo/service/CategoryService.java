package com.example.demo.service;

import com.example.demo.exception.category.CategoryNotFoundException;
import com.example.demo.exception.category.InvalidCategoryNameException;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Category create(Category category) {
        if (category.getName() == null || category.getName().trim().isEmpty()) {
            throw new InvalidCategoryNameException("Nome da categoria não pode ser vazio.");
        }
        return repository.save(category);
    }

    @Transactional(readOnly = true)
    public List<Category> findAll() { return repository.findAll(); }

    @Transactional(readOnly = true)
    public Category findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Categoria não encontrada com id: " + id));
    }

    public Category update(Long id, Category categoryDetails) {
        Category existingCategory = findById(id);
        if (categoryDetails.getName() == null || categoryDetails.getName().trim().isEmpty()) {
            throw new InvalidCategoryNameException("Nome da categoria não pode ser vazio.");
        }
        existingCategory.setName(categoryDetails.getName());
        return repository.save(existingCategory);
    }

    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}