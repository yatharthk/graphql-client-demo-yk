package com.example.graphql_client.graphql_client.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class StudentRequestInput {

    private String firstName;
    private String lastName;
    private String email;
    private String street;
    private String city;
    private List<CreateSubjectRequest> subjectsLearning;
}
