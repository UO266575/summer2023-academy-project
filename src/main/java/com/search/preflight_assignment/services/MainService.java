package com.search.preflight_assignment.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import entities.RequestInfo;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class MainService {

    public RequestInfo generateResponseElasticClient(List<String> query) {
        try {
            List<String> queryList = new ArrayList<>();
            query.forEach(queryList::add);

            return new RequestInfo(queryList, extractInformation(getPetitionInformationElasticClient()));

        } catch (Exception e) {
            throw new RuntimeException("Failed to build JSON", e);
        }
    }

    private String extractInformation(JsonNode information) {
        try {
            String versionNumber = information.get("version").get("number").asText();
            return versionNumber;
        } catch (Exception e) {
            throw new RuntimeException("Failed to extract information from JSON", e);
        }
    }

    public JsonNode getPetitionInformationElasticClient(){
        try {
            RestHighLevelClient client = new RestHighLevelClient(
                    RestClient.builder(new HttpHost("localhost", 9200, "http")));

            Request request = new Request("GET", "/");
            Response response = client.getLowLevelClient().performRequest(request);

            String responseBody = EntityUtils.toString(response.getEntity());

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            client.close();

            return jsonNode;
        } catch (IOException e) {
            throw new RuntimeException("Failed to connect to Elasticsearch", e);
        }
    }
}
