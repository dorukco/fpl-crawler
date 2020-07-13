package org.dodo.fpl.crawler

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FplDataCrawlerApplication

fun main(args: Array<String>) {
	runApplication<FplDataCrawlerApplication>(*args)
}
