package com.briannbig.products.domain;

import com.briannbig.products.repository.ProductRepository;
import com.briannbig.products.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    private final ProductRepository repository;

    public ProductsService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> fetch(){
        return repository.findAll();
    }

    public Product fetch(long id){
        return repository.findById(id).orElse(null);
    }

    public Product fetch(String name){
        return repository.findByName(name);
    }

    public Product add(String name, double price){
        return repository.save(Product.builder()
                .name(name)
                .price(price)
                .build());
    }

    @Transactional
    public Product update(long id, String name, double price){
        Optional<Product> product = repository.findById(id);
        if (product.isPresent()){
            return repository.save(Product.builder()
                    .id(id)
                    .name(name)
                    .price(price)
                    .build());
        }
        return null;
    }

    public void remove(long id){
        repository.deleteById(id);
    }

    public List<Product> products(long categoryId){
        return repository.findByCategoryId(categoryId);
    }
    public List<Product> products(String categoryName){
        return repository.findByCategoryName(categoryName);
    }
}
