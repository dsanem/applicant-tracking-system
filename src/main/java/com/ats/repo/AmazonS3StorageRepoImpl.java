package com.ats.repo;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ats.model.Applicant;
import com.ats.util.ATSUtil;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Repository
public class AmazonS3StorageRepoImpl implements ResumeStorageRepo {

    private final AmazonS3 amazonS3;

    private final String bucketName;


    public AmazonS3StorageRepoImpl(AmazonS3 amazonS3,
                                   String bucketName) {
        this.amazonS3 = amazonS3;
        this.bucketName = bucketName;
    }


    @Override
    public Mono<Long> saveResume(MultipartFile file, Applicant applicant) throws IOException {

        ObjectMetadata metadata = new ObjectMetadata();
        long fileSize = file.getSize();
        metadata.setContentLength(fileSize);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,
                ATSUtil.buildS3PathWith(applicant.getLastName(), applicant.getId()),
                file.getInputStream(),
                metadata);

        amazonS3.putObject(putObjectRequest);
        return Mono.just(fileSize);
    }

    @Override
    public Flux<Object> findResumeWithId(String id) {
        return null;
    }
}
