package org.dodo.fpl.crawler.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("fpl.draft")
class DraftFplConfiguration {
    lateinit var bootstrapStaticUrl: String
    lateinit var fixturesUrl: String
}