package com.example.graphql_client.graphql_client.controller;

import com.example.graphql_client.graphql_client.responses.StudentResponse;
import com.example.graphql_client.graphql_client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/get/{id}")
    public StudentResponse getStudent(@PathVariable Integer id){

        StudentResponse res = clientService.getStudent(id);
        return res;

    }
}
