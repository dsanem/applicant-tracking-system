package com.ats.repo;

import com.ats.model.Applicant;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Repository
public interface ResumeStorageRepo {

    public Mono<Long> saveResume(MultipartFile file, Applicant applicant) throws IOException;

    public Flux<Object> findResumeWithId(String id);
}
