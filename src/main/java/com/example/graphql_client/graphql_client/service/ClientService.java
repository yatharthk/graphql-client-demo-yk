package com.example.graphql_client.graphql_client.service;

import com.example.graphql_client.graphql_client.responses.StudentResponse;
import graphql.kickstart.spring.webclient.boot.GraphQLRequest;
import graphql.kickstart.spring.webclient.boot.GraphQLResponse;
import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    GraphQLWebClient graphQLWebClient;

    public StudentResponse getStudent(Integer id) {
        String queryString = "query \r\n " +
                "   { student(id: $id) {\n" +
                "    id\n" +
                "    firstName\n" +
                "    lastName\n" +
                "    email\n" +
                "    street\n" +
                "    city\n" +
                "    fullName\n" +
                "    learningSubjects(subjectNameFilter: [All]) {\n" +
                "      id\n" +
                "      subjectName\n" +
                "      marksObtained\n" +
                "    }\n" +
                "  } }";

        GraphQLRequest request = GraphQLRequest.builder().query(queryString).build();
        GraphQLResponse graphQLResponse = graphQLWebClient.post(request).block();
        StudentResponse studentRes = graphQLResponse.get("student", StudentResponse.class);
        return studentRes;
    }
}
