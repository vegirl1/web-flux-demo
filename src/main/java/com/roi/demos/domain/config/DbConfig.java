package com.roi.demos.domain.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.Option;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
@EnableR2dbcRepositories
public class DbConfig extends AbstractR2dbcConfiguration {

    @Bean("connectionFactory")
    @Override
    public ConnectionFactory connectionFactory() {

        var options = ConnectionFactoryOptions.builder()
            .option(DRIVER, "h2")
            .option(DATABASE, "test")
            .option(PROTOCOL, "mem")
            .option(Option.valueOf("ACCESS_MODE_DATA"), "HSQLDB")
            .option(Option.valueOf("DB_CLOSE_DELAY"), "-1")
            .option(Option.valueOf("DB_CLOSE_ON_EXIT"), "false")
            .build();

        return ConnectionFactories.get(options);
    }

    @Bean("dbInit")
    @DependsOn("connectionFactory")
    public ConnectionFactoryInitializer initDbContent(ConnectionFactory connectionFactory) {
        ConnectionFactoryInitializer cxInit = new ConnectionFactoryInitializer();
        cxInit.setConnectionFactory(connectionFactory);
        CompositeDatabasePopulator populator = new CompositeDatabasePopulator();
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("h2_schema.sql")));
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("insertDi.sql")));
        cxInit.setDatabasePopulator(populator);
        return cxInit;
    }
}
