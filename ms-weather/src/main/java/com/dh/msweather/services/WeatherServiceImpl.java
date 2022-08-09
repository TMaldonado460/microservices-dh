package com.dh.msweather.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements IWeatherService {
    @Override
    public Mono<Double> getWeather(String city, String country) {
        return Mono.just(25.0);
    }
}
