package com.example.graphql_client.graphql_client.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSubjectRequest {

    private String subjectName;
    private Double marksObtained;
}
