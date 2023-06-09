package com.search.preflight_assignment.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.search.preflight_assignment.services.MainService;
import entities.RequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    private MainService mainService;

    @RequestMapping("/search")
    public RequestInfo getQueryAndClusterName(@RequestParam("query") List<String> queryValues) {
        RequestInfo responseObject = mainService.generateResponseElasticClient(queryValues);
        return responseObject;
    }
}
