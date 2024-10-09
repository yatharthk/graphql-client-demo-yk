package com.example.graphql_client.graphql_client.service;

import com.example.graphql_client.graphql_client.responses.StudentResponse;
import graphql.kickstart.spring.webclient.boot.GraphQLRequest;
import graphql.kickstart.spring.webclient.boot.GraphQLResponse;
import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class ClientService {

    @Autowired
    GraphQLWebClient graphQLWebClient;

    public StudentResponse getStudent(Integer id) {
        String queryString = "query " +
                "   { student(id:"+id+")  {\n" +
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

        try {
            GraphQLRequest request = GraphQLRequest.builder().query(queryString).build();
            GraphQLResponse graphQLResponse = graphQLWebClient.post(request).block();
            if (null == graphQLResponse || !graphQLResponse.getErrors().isEmpty()) {
                throw new RuntimeException("Exception Response from server::" + Objects.requireNonNull(graphQLResponse).getErrors());
            }
            return graphQLResponse.get("student", StudentResponse.class);

        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

}
