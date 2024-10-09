package com.example.graphql_client.graphql_client.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class StudentResponse {

    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private String street;

    private String city;

    private String fullName;

    private List<SubjectResponse> learningSubjects;

}
