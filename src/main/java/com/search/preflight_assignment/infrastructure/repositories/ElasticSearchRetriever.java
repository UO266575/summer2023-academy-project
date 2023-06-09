package com.search.preflight_assignment.infrastructure.repositories;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.search.preflight_assignment.domain.RecordRetriever;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public record ElasticSearchRetriever(ElasticsearchClient elasticsearchClient) implements RecordRetriever {

    @Override
    public String findVersion() {
        try {
            return elasticsearchClient.info().version().number();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}