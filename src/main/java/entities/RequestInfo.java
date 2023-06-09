package entities;

import java.util.List;

public class RequestInfo {

    public final List<String> query;

    public final String clusterName;


    public RequestInfo(List<String> query, String clusterName) {
        this.clusterName = clusterName;
        this.query = List.copyOf(query);
    }
}
