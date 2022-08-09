package com.dh.msweather.services;

import reactor.core.publisher.Mono;

public interface IWeatherService {

    Mono<Double> getWeather(String city, String country);

}
