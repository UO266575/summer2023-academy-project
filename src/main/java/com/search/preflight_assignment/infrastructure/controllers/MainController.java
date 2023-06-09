package com.search.preflight_assignment.infrastructure.controllers;

import com.search.preflight_assignment.application.RecordService;
import com.search.preflight_assignment.domain.RequestInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    private final RecordService recordService;

    public MainController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping("/search")
    public RequestInfo getQueryAndClusterName(@RequestParam("query") List<String> queryValues) {
        return recordService.generateResponseElasticClient(queryValues);
    }
}
