package com.ats.service;


import com.ats.config.Profile;
import com.ats.model.Applicant;
import com.ats.repo.ApplicantRepository;
import com.ats.repo.ResumeStorageRepo;
import com.ats.service.factory.ResumeRepoFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
public class ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final ResumeRepoFactory resumeRepoFactory;
    private final Profile profile;

    @Autowired
    public ApplicantService(ApplicantRepository applicantRepository, ResumeRepoFactory resumeRepoFactory,
                            @Value("${ats.application.profile}") Profile profile) {
        this.applicantRepository = applicantRepository;
        this.resumeRepoFactory = resumeRepoFactory;
        this.profile = profile;
    }

    public Mono<Applicant> create(Applicant applicant, MultipartFile resumeFile) {

        return applicantRepository.insert(applicant)
                .flatMap(res -> findById(res.getId())
                        .flatMap(maybeApplicant -> {

                            if (StringUtils.isEmpty(maybeApplicant.getId())) {
                                return Mono.empty();
                            }

                            ResumeStorageRepo resumeStorageRepo = resumeRepoFactory.getStorageRepo(profile);
                                try {
                                    return resumeStorageRepo.saveResume(resumeFile, maybeApplicant).flatMap(fileSize -> {
                                        if (fileSize > 0) {
                                            return Mono.just(maybeApplicant);
                                        }
                                        return Mono.empty();
                                    });
                                } catch (IOException ioe) {
                                    return Mono.empty();
                                }
                        }));
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
