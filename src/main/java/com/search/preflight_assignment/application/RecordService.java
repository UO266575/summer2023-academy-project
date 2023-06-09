package com.search.preflight_assignment.application;

import com.search.preflight_assignment.domain.RecordRetriever;
import com.search.preflight_assignment.domain.RequestInfo;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RecordService {

    public final RecordRetriever recordRetriever;

    public RecordService(RecordRetriever recordRetriever) {
        this.recordRetriever = recordRetriever;
    }

    public RequestInfo generateResponseElasticClient(List<String> query) {
        return new RequestInfo(query, recordRetriever.findVersion());
    }
}
