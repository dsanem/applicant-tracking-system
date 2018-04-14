package com.ats.repo;

import com.ats.model.Applicant;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Repository
public class GoogleStorageRepoImpl implements ResumeStorageRepo {

    private final Storage googleStorage;
    private final String bucketName;

    public GoogleStorageRepoImpl(Storage googleStorage, String bucketName) {
        this.googleStorage = googleStorage;
        this.bucketName = bucketName;
    }

    @Override
    public Mono<Long> saveResume(MultipartFile file, Applicant applicant) throws IOException {
        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, applicant.getId()).build();

        Blob blob = googleStorage.create(blobInfo, file.getBytes());
        long size = blob.getSize();
        return Mono.just(size);
    }

    @Override
    public Flux<Object> findResumeWithId(String id) {
        return null;
    }
}
