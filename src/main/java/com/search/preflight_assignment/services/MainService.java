package com.search.preflight_assignment.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public String generateResponse(List<String> query) {
        String version = extractInformation(getPetitionInformation("http://localhost:9200/"));
        return "hola";
    }

    private String extractInformation(String information) {
        try {
            // Parse el JSON
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(information);

            // Obtenga el valor de "version number"
            String versionNumber = jsonNode.get("version").get("number").asText();

            return versionNumber;
        } catch (Exception e) {
            throw new RuntimeException("Failed to extract information from JSON", e);
        }
    }

    public String getPetitionInformation(String uri){
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(uri);

            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                String responseBody = EntityUtils.toString(response.getEntity());

                return responseBody;
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to connect to URL: " + uri, e);
        }
    }
}
