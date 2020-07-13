package org.dodo.fpl.crawler.config

import org.apache.http.HttpHost
import org.apache.http.auth.AuthScope
import org.apache.http.auth.UsernamePasswordCredentials
import org.apache.http.impl.client.BasicCredentialsProvider
import org.elasticsearch.client.RestClient
import org.elasticsearch.client.RestHighLevelClient
import org.jetbrains.annotations.NotNull
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "elasticsearch")
@ConditionalOnProperty("elasticsearch.url")
class ElasticsearchConfiguration {

    @NotNull
    lateinit var url: String
    lateinit var username: String
    lateinit var password: String
    var authenticationEnabled = false

    @Bean(destroyMethod = "close")
    fun restHighLevelClient(): RestHighLevelClient {
        return if (authenticationEnabled) {
            val credentialsProvider = BasicCredentialsProvider()
            credentialsProvider.setCredentials(AuthScope.ANY, UsernamePasswordCredentials(username, password))
            RestHighLevelClient(RestClient.builder(HttpHost.create(url))
                    .setHttpClientConfigCallback { builder -> builder.setDefaultCredentialsProvider(credentialsProvider) })
        } else {
            RestHighLevelClient(RestClient.builder(HttpHost.create(url)))
        }
    }
}