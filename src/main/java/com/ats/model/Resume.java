package com.ats.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Document(collection = "applicant")
public class Resume {

    @Id
    private Integer id;

    private String resumeUrl;
}
