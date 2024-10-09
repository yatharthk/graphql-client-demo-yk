package com.example.graphql_client.graphql_client.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectResponse {

    private Long id;

    private String subjectName;

    private Double marksObtained;

}