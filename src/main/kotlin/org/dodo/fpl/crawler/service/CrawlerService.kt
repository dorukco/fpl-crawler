package org.dodo.fpl.crawler.service

import com.fasterxml.jackson.databind.ObjectMapper
import mu.KLogging
import org.dodo.fpl.crawler.config.DraftFplConfiguration
import org.dodo.fpl.crawler.index.IndexService
import org.springframework.boot.CommandLineRunner
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import java.time.ZonedDateTime

@Component
class CrawlerService(
        private val fplConfiguration: DraftFplConfiguration,
        private val webClientBuilder: WebClient.Builder,
        private val objectMapper: ObjectMapper,
        private val indexService: IndexService
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        retrievePlayerStats().map {
            logger.info { it }
            indexService.index(objectMapper.writeValueAsString(it.apply { it.updateTime = ZonedDateTime.now() }), it?.id.toString(), "players")
        }.blockLast()

        retrieveFixtures().map {
            logger.info { it }
            indexService.index(objectMapper.writeValueAsString(it.apply { it.updateTime = ZonedDateTime.now() }), it?.id.toString(), "fixtures")
        }.blockLast()
    }

    fun retrievePlayerStats(): Flux<Element> = webClientBuilder
            .baseUrl(fplConfiguration.bootstrapStaticUrl)
            .build().get()
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(BootStrapStaticResponse::class.java)
            .map { rep -> rep.elements }
            .flatMapMany { Flux.fromIterable(it) }

    fun retrieveFixtures(): Flux<FixtureResponse> = webClientBuilder
            .baseUrl(fplConfiguration.fixturesUrl)
            .build().get()
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToFlux(FixtureResponse::class.java)

    companion object : KLogging()
}