package com.ats.service;

import com.ats.model.Applicant;
import com.ats.repo.ResumeStorageRepo;
import com.ats.service.factory.ResumeRepoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ResumeService {
    private ResumeStorageRepo resumeStorageRepo;
    private final ResumeRepoFactory resumeRepoFactory;

    @Autowired
    public ResumeService(ResumeRepoFactory resumeRepoFactory) {
        this.resumeRepoFactory = resumeRepoFactory;
    }

    public Mono<Applicant> findApplicantResumesByLastName(String lastName) {
        return null;
    }

}
