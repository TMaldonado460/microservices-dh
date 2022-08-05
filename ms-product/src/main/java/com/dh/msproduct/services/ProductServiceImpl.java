package com.dh.msproduct.services;


import com.dh.msproduct.models.Product;
import com.dh.msproduct.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Flux<Product> findAllProducts() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<Product> findProductById(String idProduct) {
        return repository.findById(idProduct);
    }

    @Override
    public Mono<Product> saveProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public Mono<Void> deleteProduct(Product product) {
        return repository.delete(product);
    }
}
