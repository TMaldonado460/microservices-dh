package com.dh.msproduct.config;

import com.dh.msproduct.handlers.ProductHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterFunctionConfig {
    
    private static final String PRODUCT_ROUTE = "/api/v1/products";
    private static final String PRODUCT_ID_ROUTE = "/api/v1/products/{id}";
    
    @Bean
    public RouterFunction<ServerResponse> routes(ProductHandler handler) {
        return null;
    }
    
}
