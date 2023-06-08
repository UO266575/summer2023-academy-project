package com.search.preflight_assignment.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.search.preflight_assignment.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private MainService mainService;

    @RequestMapping("/search")
    public ResponseEntity<ObjectNode> getQueryAndClusterName(@RequestParam("query") List<String> queryValues) {
        ObjectNode responseObject = mainService.generateResponse(queryValues);
        return ResponseEntity.ok(responseObject);
    }
}
