package com.ats.resource;

import com.ats.model.Applicant;
import com.ats.service.ApplicantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.io.IOException;
import java.util.Set;

@RestController
@Slf4j
public class ApplicantResource {

    private final ApplicantService applicantService;
    private final ObjectMapper mapper;
    private final Validator validator;


    @Autowired
    public ApplicantResource(ApplicantService applicantService, ObjectMapper mapper,
                             @Qualifier("ATSValidator") Validator validator) {
        this.applicantService = applicantService;
        this.mapper = mapper;
        this.validator = validator;
    }

    @PostMapping(path = "/applicant",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<Applicant> addApplicant(@RequestParam("file") MultipartFile file, @RequestParam("body") String maybeApplicant) throws IOException {

        Applicant applicant = mapper.readValue(maybeApplicant, Applicant.class);
        Set<ConstraintViolation<Applicant>> constraintViolations = validator.validate(applicant, Default.class);

        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }

        return applicantService.create(applicant, file);
    }

    @GetMapping(path = "/applicant/firstname/{firstName}",
            produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Applicant> findAllApplicantsWithFirstName(@PathVariable("firstName") String firstName) {
        return applicantService.findByFirstName(firstName);
    }

    @GetMapping(path = "/applicant/id/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Applicant> findApplicantById(@PathVariable("id") String id) {
        return applicantService.findById(id);
    }

    @GetMapping(path = "/applicant/phonenumber/{phoneNumber}")
    public Flux<Applicant> findApplicantByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
        return applicantService.findByPhoneNumber(phoneNumber);
    }


    @PutMapping(path = "/applicant/{id}")
    public Mono<Applicant> updateApplicant(@RequestBody @Valid Applicant applicant, @PathVariable("id") String id) {
        return applicantService.updateById(applicant, id);
    }
}
