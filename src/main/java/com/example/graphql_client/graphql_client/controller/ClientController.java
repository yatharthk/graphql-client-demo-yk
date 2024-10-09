package com.example.graphql_client.graphql_client.controller;

import com.example.graphql_client.graphql_client.requests.StudentRequestInput;
import com.example.graphql_client.graphql_client.responses.StudentResponse;
import com.example.graphql_client.graphql_client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/get/{id}")
    public StudentResponse getStudent(@PathVariable Integer id){
        return clientService.getStudent(id);

    }

    @PostMapping("/create")
    public StudentResponse createStudent(@RequestBody StudentRequestInput studentRequestInput){
        return clientService.createStudent(studentRequestInput);
    }
}
