package com.ats.service.factory;

import com.amazonaws.services.s3.AmazonS3;
import com.ats.config.Profile;
import com.ats.repo.AmazonS3StorageRepoImpl;
import com.ats.repo.GoogleStorageRepoImpl;
import com.ats.repo.ResumeStorageRepo;
import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ResumeRepoFactory {


    private final AmazonS3 amazonS3;

    private final Storage storage;

    private final String awsBucketName;

    private final String googleBucketName;


    @Autowired
    public ResumeRepoFactory(AmazonS3 amazonS3,
                             Storage storage,
                             @Value("${aws.s3.bucketName}") String awsBucketName,
                             @Value("${google.bucket.name}") String googleBucketName) {
        this.amazonS3 = amazonS3;
        this.storage = storage;
        this.googleBucketName = googleBucketName;
        this.awsBucketName = awsBucketName;
    }

    public ResumeStorageRepo getStorageRepo(Profile profile) {

        //Modify this as you get more ways to store

        switch (profile) {
            case NON_PROD:
                return new AmazonS3StorageRepoImpl(amazonS3, awsBucketName);
            case PROD:
                return new AmazonS3StorageRepoImpl(amazonS3, awsBucketName);
            default:
                return new GoogleStorageRepoImpl(storage, googleBucketName);

        }
    }
}
