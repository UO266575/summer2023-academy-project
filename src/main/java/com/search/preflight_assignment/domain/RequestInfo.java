package com.search.preflight_assignment.domain;

import java.util.List;

public record RequestInfo(List<String> query, String clusterName) {

    public RequestInfo(List<String> query, String clusterName) {
        this.clusterName = clusterName;
        this.query = List.copyOf(query);
    }
}
