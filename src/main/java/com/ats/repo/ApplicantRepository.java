package com.ats.repo;

import com.ats.model.Applicant;
import com.mongodb.client.result.UpdateResult;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ApplicantRepository {
    Mono<Applicant> insert(Applicant applicant);

    Flux<Applicant> findApplicantByFirstName(String firstName);

    Flux<Applicant> findApplicantByLastName(String lastName);

    Mono<Applicant> findApplicantById(String id);

    Flux<Applicant> findApplicantByPhoneNumber(String phoneNumber);

    Mono<UpdateResult> update(Applicant applicant, String id);
}
