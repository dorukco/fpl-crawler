package org.dodo.fpl.crawler.config

import mu.KLogging
import org.dodo.fpl.crawler.service.CrawlerService
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

@Configuration
@EnableScheduling
class CronConfiguration(
        private val crawlerService: CrawlerService
) {

    @Scheduled(cron = "\${update.cron}")
    fun scheduleFixedDelayTask() {
        logger.info { "Cron is starting..." }
        crawlerService.run()
    }

    companion object : KLogging()
}