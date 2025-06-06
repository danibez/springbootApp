package com.example.demo.service;

import com.example.demo.exception.category.CategoryNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Cria uma nova categoria.
     * @param category A categoria a ser salva.
     * @return A categoria salva com seu ID.
     */
    public Category createCategory(Category category) {
        // Futuramente, você pode adicionar uma validação para não permitir
        // categorias com nomes duplicados.
        return categoryRepository.save(category);
    }

    /**
     * Retorna uma lista de todas as categorias.
     * @return Lista de categorias.
     */
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Busca uma categoria pelo seu ID.
     * Lança CategoryNotFoundException se não encontrar.
     * @param id O ID da categoria.
     * @return A categoria encontrada.
     */
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Categoria não encontrada com o id: " + id));
    }

    /**
     * Atualiza o nome de uma categoria existente.
     * @param id O ID da categoria a ser atualizada.
     * @param categoryDetails Os novos detalhes da categoria.
     * @return A categoria atualizada.
     */
    public Category updateCategory(Long id, Category categoryDetails) {
        Category existingCategory = findCategoryById(id);
        existingCategory.setName(categoryDetails.getName());
        return categoryRepository.save(existingCategory);
    }

    /**
     * Deleta uma categoria pelo seu ID.
     * @param id O ID da categoria a ser deletada.
     */
    public void deleteCategory(Long id) {
        // A linha abaixo já lança a exceção se a categoria não for encontrada.
        Category category = findCategoryById(id);
        categoryRepository.delete(category);
    }
}