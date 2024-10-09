package com.example.graphql_client.graphql_client.service;

import com.example.graphql_client.graphql_client.enums.SubjectFilter;
import com.example.graphql_client.graphql_client.requests.StudentRequestInput;
import com.example.graphql_client.graphql_client.responses.StudentResponse;
import graphql.kickstart.spring.webclient.boot.GraphQLRequest;
import graphql.kickstart.spring.webclient.boot.GraphQLResponse;
import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

        String queryString_withVariables = """
                query student($id: Int) \
                   { student(id:$id)  {
                    id
                    firstName
                    lastName
                    email
                    street
                    city
                    fullName
                    learningSubjects(subjectNameFilter: [All]) {
                      id
                      subjectName
                      marksObtained
                    }
                  } }""";
        Map<String,Object> variables = new HashMap<>();
        variables.put("id",id);

        try {
            GraphQLRequest request = GraphQLRequest.builder().query(queryString).build();
            GraphQLRequest request2 = GraphQLRequest.builder().query(queryString_withVariables).variables(variables).build();

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

    public StudentResponse createStudent(StudentRequestInput studentRequestInput) {
        String queryString = """
                mutation createStudent($createRequest:StudentRequestInput,$subjectName:[SubjectFilter])\
                {
                  \
                createStudent(createStudentRequest:$createRequest ) \
                {
                    \
                id
                    \
                firstName
                    lastName
                    email
                    \
                street
                    city
                    fullName
                    \
                learningSubjects(subjectNameFilter: $subjectName) \
                {
                      id
                      \
                subjectName
                      \
                marksObtained
                    \
                }
                  \
                }
                }""";

        List<SubjectFilter> subjectFilterList = new ArrayList<>();
        subjectFilterList.add(SubjectFilter.All);


        Map<String, Object> variables = new HashMap<>();
        variables.put("createRequest", studentRequestInput);
        variables.put("subjectName", subjectFilterList);

        try {
            GraphQLRequest request = GraphQLRequest.builder().query(queryString).variables(variables).build();
            GraphQLResponse response = graphQLWebClient.post(request).block();
            if (response != null && !response.getErrors().isEmpty() || Objects.requireNonNull(response).get("createStudent",Object.class)==null) {
                throw new RuntimeException("Exception response from graphql service");
            }
            return response.get("createStudent", StudentResponse.class);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
