package com.dh.msweather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsWeatherApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsWeatherApplication.class, args);
    }

}
