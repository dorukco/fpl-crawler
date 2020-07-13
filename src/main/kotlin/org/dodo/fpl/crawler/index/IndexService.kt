package org.dodo.fpl.crawler.index

import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.client.RequestOptions
import org.elasticsearch.client.RestHighLevelClient
import org.elasticsearch.common.xcontent.XContentType
import org.springframework.stereotype.Service

@Service
class IndexService(
        private val restHighLevelClient: RestHighLevelClient
) {

    fun index(payload: String, id: String, name: String) {
        val indexRequest = IndexRequest(name).apply {
            id(id)
            source(payload, XContentType.JSON)
        }
        restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT)
    }

}