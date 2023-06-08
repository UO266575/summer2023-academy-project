package com.search.preflight_assignment.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
public class MainService {

    public ObjectNode generateResponse(List<String> query) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            ObjectNode jsonNode = objectMapper.createObjectNode();

            ArrayNode queryArrayNode = objectMapper.createArrayNode();
            for (String q : query) {
                queryArrayNode.add(q);
            }

            jsonNode.put("query", queryArrayNode);
            jsonNode.put("clusterName", extractInformation(getPetitionInformation("http://localhost:9200/")));

            return jsonNode;
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

    public JsonNode getPetitionInformation(String uri){
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(uri);

            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                String responseBody = EntityUtils.toString(response.getEntity());

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(responseBody);

                return jsonNode;
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to connect to URL: " + uri, e);
        }
    }
}
