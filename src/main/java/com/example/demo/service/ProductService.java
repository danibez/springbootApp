package com.example.demo.service;

import com.example.demo.exception.category.CategoryNotFoundException;
import com.example.demo.exception.product.InvalidProductNameException;
import com.example.demo.exception.product.InvalidProductPriceException;
import com.example.demo.exception.product.ProductNotFoundException;
import com.example.demo.exception.user.UserNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.model.Seller;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired private ProductRepository repository;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private SellerRepository sellerRepository;

    public Product create(Product product) {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new InvalidProductNameException("Nome do produto não pode ser vazio.");
        }
        if (product.getPrice() == null || product.getPrice() <= 0) {
            throw new InvalidProductPriceException("Preço do produto deve ser maior que zero.");
        }

        Category category = categoryRepository.findById(product.getCategory().getId())
                .orElseThrow(() -> new CategoryNotFoundException("Categoria não encontrada."));

        Seller seller = sellerRepository.findById(product.getSeller().getId())
                .orElseThrow(() -> new UserNotFoundException("Vendedor não encontrado."));

        product.setCategory(category);
        product.setSeller(seller);
        return repository.save(product);
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() { return repository.findAll(); }

    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException("Produto não encontrado."));
    }

    public Product update(Long id, Product productDetails) {
        Product existingProduct = findById(id);

        if (productDetails.getName() == null || productDetails.getName().trim().isEmpty()) {
            throw new InvalidProductNameException("Nome do produto não pode ser vazio.");
        }
        if (productDetails.getPrice() == null || productDetails.getPrice() <= 0) {
            throw new InvalidProductPriceException("Preço do produto deve ser maior que zero.");
        }

        // Se uma nova categoria for fornecida, verifique se ela existe
        if (productDetails.getCategory() != null && productDetails.getCategory().getId() != null) {
            Category category = categoryRepository.findById(productDetails.getCategory().getId())
                    .orElseThrow(() -> new CategoryNotFoundException("Categoria não encontrada."));
            existingProduct.setCategory(category);
        }

        existingProduct.setName(productDetails.getName());
        existingProduct.setDescription(productDetails.getDescription());
        existingProduct.setPrice(productDetails.getPrice());

        return repository.save(existingProduct);
    }

    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}