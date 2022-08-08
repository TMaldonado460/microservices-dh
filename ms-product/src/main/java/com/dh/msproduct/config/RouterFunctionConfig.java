package com.dh.msproduct.config;

import com.dh.msproduct.handlers.ProductHandler;
import com.dh.msproduct.models.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;

@Configuration
public class RouterFunctionConfig {

    private static final String PRODUCT = "/api/v1/productos";
    private static final String PRODUCT_ID = "/api/v1/productos/{id}";

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = PRODUCT,
                    produces = {
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    method = RequestMethod.GET,
                    beanClass = ProductHandler.class,
                    beanMethod = "listar",
                    operation = @Operation(
                            operationId = "loadProducts",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "successful operation",
                                            content = @Content(schema = @Schema(
                                                    implementation = Product.class
                                            ))
                                    )
                            }
                    )
            ),
            @RouterOperation(path = PRODUCT_ID, beanClass = ProductHandler.class, beanMethod = "ver"),
            @RouterOperation(path = PRODUCT, beanClass = ProductHandler.class, beanMethod = "crear"),
            @RouterOperation(path = PRODUCT_ID, beanClass = ProductHandler.class, beanMethod = "editar"),
            @RouterOperation(path = PRODUCT_ID, beanClass = ProductHandler.class, beanMethod = "eliminar")})
    public RouterFunction<ServerResponse> routes(ProductHandler handler) {
        return RouterFunctions.route(GET(PRODUCT), handler::listar)
                .andRoute(GET(PRODUCT_ID), handler::ver)
                .andRoute(POST(PRODUCT), handler::crear)
                .andRoute(PUT(PRODUCT_ID), handler::actualizar)
                .andRoute(DELETE(PRODUCT_ID), handler::eliminar)
                ;
    }
}
