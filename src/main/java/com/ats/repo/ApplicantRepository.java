package com.ats.repo;

import com.ats.model.Applicant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ApplicantRepository extends ReactiveCrudRepository<Applicant, Integer> {

    @Query("SELECT a FROM Applicant a WHERE a.firstName = :firstName")
    Flux<Applicant> finaApplicantByFirstName(@Param("firstName") String firstName);
}
