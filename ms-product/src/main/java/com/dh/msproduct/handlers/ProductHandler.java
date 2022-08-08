package com.dh.msproduct.handlers;

import com.dh.msproduct.models.Product;
import com.dh.msproduct.services.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@RequiredArgsConstructor
public class ProductHandler {

    private final IProductService service;
    private final Validator validator;

    public Mono<ServerResponse> listar(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(APPLICATION_JSON)
                .body(service.findAllProducts(), Product.class);
    }

    @SneakyThrows
    public Mono<ServerResponse> ver(ServerRequest request) {
        var id = request.pathVariable("id");
        return service.findProductById(id)
                .flatMap(product ->
                        ServerResponse.ok().body(BodyInserters.fromValue(product)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> crear(ServerRequest request) {
        var product = request.bodyToMono(Product.class);
        return product.flatMap(p -> {
            //Validar Errores
            Errors errors = new BeanPropertyBindingResult(p, Product.class.getName());
            validator.validate(p, errors);
            if (errors.hasErrors()) {
                return Flux.fromIterable(errors.getFieldErrors())
                        .map(fieldError -> String.format("El campo %s %s", fieldError.getField(),
                                fieldError.getDefaultMessage()))
                        .collectList()
                        .flatMap(list -> ServerResponse.badRequest().body(BodyInserters.fromValue(list)));
            } // Fin de Validar Errores
            else {
                if (p.getCreatedAt() == null) p.setCreatedAt(LocalDateTime.now());
                return service.saveProduct(p)
                        .flatMap(productSaved ->
                                ServerResponse
                                        .created(URI.create(("/api/v1/products/".concat(productSaved.getIdProduct()))))
                                        .contentType(APPLICATION_JSON)
                                        .body(BodyInserters.fromValue(productSaved)));
            }
        });
    }

    public Mono<ServerResponse> actualizar(ServerRequest request) {
        var id = request.pathVariable("id");
        var product = request.bodyToMono(Product.class);
        var productDB = service.findProductById(id);

        return productDB.zipWith(product, (pdb, req) -> {
            pdb.setName(req.getName());
            pdb.setPrice(req.getPrice());
            pdb.setCreatedAt(req.getCreatedAt());
            return pdb;
        }).flatMap(productSaved ->
                ServerResponse
                        .created(URI.create(("/api/v1/products/".concat(productSaved.getIdProduct()))))
                        .contentType(APPLICATION_JSON)
                        .body(BodyInserters.fromValue(productSaved)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> eliminar(ServerRequest request) {
        var id = request.pathVariable("id");
        return service.findProductById(id)
                .flatMap(product ->
                        service.deleteProduct(product)
                                .then(ServerResponse.accepted().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }


}
