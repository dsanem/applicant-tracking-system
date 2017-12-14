package com.ats.service;


import com.ats.model.Applicant;
import com.ats.repo.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ApplicantService {

    private final ApplicantRepository applicantRepository;

    @Autowired
    public ApplicantService(ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
    }

    public Flux<Applicant> findAllApplicantsByFirstName(String firstName) {
        return applicantRepository.finaApplicantByFirstName(firstName);
    }
}
