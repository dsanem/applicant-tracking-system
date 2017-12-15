package com.ats.resource;

import com.ats.model.Applicant;
import com.ats.repo.ResumeStorageRepo;
import com.ats.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.io.IOException;

@RestController
public class ApplicantResource {

    private final ApplicantService applicantService;

    @Autowired
    public ApplicantResource(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @GetMapping(path = "/applicant",
            produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Applicant> findAllApplicantsWithFirstName(@PathParam("firstName") String firstName) {
        return applicantService.findByFirstName(firstName);
    }

    @GetMapping(path = "/applicant",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Applicant> findApplicantById(@PathParam("id") String id) {
        return applicantService.findById(id);
    }

    @GetMapping(path = "/applicant")
    public Flux<Applicant> findApplicantByPhoneNumber(@PathParam("phoneNumber") String phoneNumber) {
        return applicantService.findByPhoneNumber(phoneNumber);
    }

    @PostMapping(path = "/applicant")
    public Mono<Applicant> addApplicant(@RequestParam("file") MultipartFile file, @RequestBody @Valid Applicant applicant) {
        return applicantService.create(applicant);
    }

    @PutMapping(path = "/applicant/{id}")
    public Mono<Applicant> updateApplicant(@RequestBody @Valid Applicant applicant, @PathVariable("id") String id) {
        return applicantService.updateById(applicant, id);
    }
}
