package com.dh.msweather.handlers;

import com.dh.msweather.services.IWeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@RequiredArgsConstructor
public class WeatherHandler {

    private final IWeatherService service;
    private final Validator validator;

    public Mono<ServerResponse> getWeather(ServerRequest request) {
        var city = request.queryParam("city").orElse("");
        var country = request.queryParam("country").orElse("");
        return ServerResponse
                .ok()
                .contentType(APPLICATION_JSON)
                .body(service.getWeather(city, country), Double.class);
    }
}
