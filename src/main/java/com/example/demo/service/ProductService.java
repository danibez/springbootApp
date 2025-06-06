package com.example.demo.service;

import com.example.demo.exception.product.ProductNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.model.Seller;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SellerRepository sellerRepository;

    /**
     * Cria um novo produto, validando a existência da Categoria e do Vendedor.
     * Lança ProductNotFoundException se a categoria ou o vendedor não forem encontrados.
     */
    public Product createProduct(Product product) {
        // Busca a Categoria pelo ID. Lança a exceção se não encontrar.
        Category category = categoryRepository.findById(product.getCategory().getId())
                .orElseThrow(() -> new ProductNotFoundException("Categoria não encontrada com o id: " + product.getCategory().getId()));

        // Busca o Vendedor pelo ID. Lança a exceção se não encontrar.
        Seller seller = sellerRepository.findById(product.getSeller().getId())
                .orElseThrow(() -> new ProductNotFoundException("Vendedor não encontrado com o id: " + product.getSeller().getId()));

        // Associa as entidades encontradas ao produto
        product.setCategory(category);
        product.setSeller(seller);

        return productRepository.save(product);
    }

    /**
     * Retorna uma lista de todos os produtos.
     */
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Busca um produto pelo seu ID.
     */
    public Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado com o id: " + id));
    }

    /**
     * Atualiza os dados de um produto existente.
     */
    public Product updateProduct(Long id, Product productDetails) {
        Product existingProduct = findProductById(id);

        existingProduct.setName(productDetails.getName());
        existingProduct.setDescription(productDetails.getDescription());
        existingProduct.setPrice(productDetails.getPrice());

        // Se uma nova categoria for fornecida, valida e a atualiza.
        if (productDetails.getCategory() != null && productDetails.getCategory().getId() != null) {
            Category category = categoryRepository.findById(productDetails.getCategory().getId())
                    .orElseThrow(() -> new ProductNotFoundException("Categoria não encontrada com o id: " + productDetails.getCategory().getId()));
            existingProduct.setCategory(category);
        }

        return productRepository.save(existingProduct);
    }

    /**
     * Deleta um produto pelo seu ID.
     */
    public void deleteProduct(Long id) {
        // Garante que o produto existe antes de deletar.
        Product product = findProductById(id);
        productRepository.delete(product);
    }
}
