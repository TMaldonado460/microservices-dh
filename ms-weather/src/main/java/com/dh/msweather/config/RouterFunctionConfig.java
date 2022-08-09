package com.dh.msweather.config;

import com.dh.msweather.handlers.WeatherHandler;
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

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RouterFunctionConfig {

    private static final String WEATHER = "/api/v1/weather";

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = WEATHER,
                    produces = {
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    method = RequestMethod.GET,
                    beanClass = WeatherHandler.class,
                    beanMethod = "listar",
                    operation = @Operation(
                            operationId = "loadProducts",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "successful operation",
                                            content = @Content(schema = @Schema(
                                                    implementation = Double.class
                                            ))
                                    )
                            }
                    )
            )})
    public RouterFunction<ServerResponse> routes(WeatherHandler handler) {
        return RouterFunctions.route(GET(WEATHER), handler::getWeather);
    }
}
