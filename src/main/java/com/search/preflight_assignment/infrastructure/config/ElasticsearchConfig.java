package com.search.preflight_assignment.infrastructure.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfig {

    @Value("${elastic.port}")
    private int port;
    @Value("${elastic.hostname}")
    private String hostname;
    @Value("${elastic.scheme}")
    private String scheme;
    private final ObjectMapper objectMapper;

    public ElasticsearchConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    public ElasticsearchClient elasticSearchClient() {
        HttpHost host = new HttpHost(hostname, port, scheme);
        RestClient restClient = RestClient.builder(host).build();
        RestClientTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper(objectMapper));
        return new ElasticsearchClient(transport);
    }

}
