package com.ats.repo;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Repository
public interface ResumeStorageRepo {

    public Mono<Long> saveResume(Object file, String id) throws IOException;

    public Flux<Object> findResumeWithId(String id);
}
