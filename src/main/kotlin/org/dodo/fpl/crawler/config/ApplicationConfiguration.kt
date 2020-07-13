package org.dodo.fpl.crawler.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.afterburner.AfterburnerModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

@Configuration
class ApplicationConfiguration {

    @Bean
    fun objectMapper(): ObjectMapper = Jackson2ObjectMapperBuilder()
            .modules(KotlinModule(), Jdk8Module(), JavaTimeModule(), AfterburnerModule())
            .serializationInclusion(JsonInclude.Include.NON_NULL)
            .featuresToEnable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY)
            .featuresToDisable(
                    SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
            ).build()
}