package com.dh.msproduct.services;

import com.dh.msproduct.models.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductService {

    Flux<Product> findAllProducts();

    Mono<Product> findProductById(String idProduct);

    Mono<Product> saveProduct(Product product);

    Mono<Void> deleteProduct(Product product);

}
