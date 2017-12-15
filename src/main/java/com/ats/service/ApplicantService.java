package com.ats.service;


import com.ats.model.Applicant;
import com.ats.repo.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ApplicantService {

    private final ApplicantRepository applicantRepository;

    @Autowired
    public ApplicantService(ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
    }

    public Mono<Applicant> create(Applicant applicant) {

        return applicantRepository.insert(applicant)
                .flatMap(result -> findById(result.getId()));
    }

    public Mono<Applicant> findById(String id) {
        return applicantRepository.findApplicantById(id);
    }

    public Flux<Applicant> findByFirstName(String firstName) {
        return applicantRepository.findApplicantByFirstName(firstName);
    }

    public Flux<Applicant> findByLastName(String lastName) {
        return applicantRepository.findApplicantByLastName(lastName);
    }

    public Flux<Applicant> findByPhoneNumber(String phoneNumber) {
        return applicantRepository.findApplicantByPhoneNumber(phoneNumber);
    }

    public Mono<Applicant> updateById(Applicant applicant, String id) {
        return applicantRepository.update(applicant, id)
                .flatMap(element -> findById(id));
    }
}
