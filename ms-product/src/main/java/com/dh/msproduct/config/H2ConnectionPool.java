package com.dh.msproduct.config;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@Configuration
public class H2ConnectionPool {

    @Bean
    ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

        var initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);

        var populate = new CompositeDatabasePopulator();
        populate.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
        populate.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("data.sql")));
        initializer.setDatabasePopulator(populate);

        return initializer;
    }

}
