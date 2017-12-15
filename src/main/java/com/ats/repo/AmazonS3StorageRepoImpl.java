package com.ats.repo;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;

@Repository
public class AmazonS3StorageRepoImpl implements ResumeStorageRepo {

    private final AmazonS3 amazonS3;

    private final String bucketName;

    @Autowired
    public AmazonS3StorageRepoImpl(AmazonS3 amazonS3,
                                   @Value("${aws.s3.bucketName}") String bucketName) {
        this.amazonS3 = amazonS3;
        this.bucketName = bucketName;
    }


    @Override
    public Mono<Long> saveResume(MultipartFile file, String path) throws IOException {

        ObjectMetadata metadata = new ObjectMetadata();
        long fileSize = file.getSize();
        metadata.setContentLength(fileSize);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,
                path,
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
