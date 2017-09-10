package com.soutvoid.tvpr.config

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder
import org.springframework.boot.bind.RelaxedPropertyResolver
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.EnvironmentAware
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.env.Environment
import javax.sql.DataSource


@Configuration
class DatabaseConfig: EnvironmentAware{

    private lateinit var propertyResolver: RelaxedPropertyResolver;

    override fun setEnvironment(p0: Environment?) {
        propertyResolver = RelaxedPropertyResolver(p0)
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    fun dataSource(): DataSource {
        return DataSourceBuilder.create()
                .url(propertyResolver.getProperty("SPRING_DATASOURCE_URL"))
                .username(propertyResolver.getProperty("SPRING_DATASOURCE_USERNAME"))
                .password(propertyResolver.getProperty("SPRING_DATASOURCE_PASSWORD"))
                .build()
    }
}