package com.ats.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Getter
@Document(collection = "applicant")
public class Applicant {

    @Id
    private String id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String phoneNumber;

    @JsonIgnore
    private String resumeId;

    @JsonIgnore
    private Status status;

    @JsonIgnore
    private Timestamp createdOn;

    @JsonIgnore
    private Timestamp updatedOn;
}
