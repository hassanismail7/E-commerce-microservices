package com.hassan.ecommerce.inventory_service.service;

import com.hassan.ecommerce.inventory_service.dto.CreateProductRequest;
import com.hassan.ecommerce.inventory_service.dto.UpdateProductRequest;
import com.hassan.ecommerce.inventory_service.entity.Inventory;
import com.hassan.ecommerce.inventory_service.entity.Product;
import com.hassan.ecommerce.inventory_service.exception.ProductNotFoundException;
import com.hassan.ecommerce.inventory_service.repository.Inventoryrepository;
import com.hassan.ecommerce.inventory_service.repository.Inventoryrepository;
import com.hassan.ecommerce.inventory_service.repository.Productrepository;
import com.hassan.ecommerce.inventory_service.repository.Productrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private Productrepository productRepository;

    @Autowired
    private Inventoryrepository inventoryRepository;

    @Autowired
    private  ImageService imageService;

    public Product createProduct(CreateProductRequest request, MultipartFile image) {

        String url = imageService.uploadImage(image);
        Product product = new Product(
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getCategory(),
                url,
                request.isBestSeller()
        );

        Inventory inventory = new Inventory(product, 0);

        // Set both sides of the relationship
        product.setInventory(inventory);

        return productRepository.save(product);
    }

    public Product getProductById(Long productId) {

        return productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found."));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(Long productId,
                                 UpdateProductRequest request) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found."));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory());
        product.setImageUrl(request.getImageUrl());
        product.setBestSeller(request.isBestSeller());

        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found."));

        productRepository.delete(product);
    }
}