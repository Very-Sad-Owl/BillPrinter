package com.epam.news.service.impl;

import com.epam.news.model.Product;
import com.epam.news.repository.models_repo.ProductRepository;
import com.epam.news.service.models_service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends AbstractService<Product, ProductRepository> implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository){this.productRepository = productRepository;}


    @Override
    public Product findById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }
}
