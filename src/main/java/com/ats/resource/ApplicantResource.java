package com.ats.resource;

import com.ats.model.Applicant;
import com.ats.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ApplicantResource {

    private final ApplicantService applicantService;

    @Autowired
    public ApplicantResource(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @GetMapping("/applicants/{firstName}")
    public Flux<ResponseEntity<Applicant>> findAllApplicants(@PathVariable("firstName") String firstName) {
        return applicantService.findAllApplicantsByFirstName(firstName)
                .map(applicant -> new ResponseEntity<>(applicant, HttpStatus.CREATED));
    }
}
