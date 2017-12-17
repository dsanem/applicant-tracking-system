package com.ats.service.factory;

import com.amazonaws.services.s3.AmazonS3;
import com.ats.config.Profile;
import com.ats.repo.AmazonS3StorageRepoImpl;
import com.ats.repo.ResumeStorageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ResumeRepoFactory {


    protected final AmazonS3 amazonS3;

    protected final String bucketName;


    @Autowired
    public ResumeRepoFactory(AmazonS3 amazonS3,
                             @Value("${aws.s3.bucketName}") String bucketName) {
        this.amazonS3 = amazonS3;
        this.bucketName = bucketName;
    }

    public ResumeStorageRepo getStorageRepo(Profile profile) {

        //Modify this as you get more ways to store

        switch (profile) {
            case NON_PROD:
                return new AmazonS3StorageRepoImpl(amazonS3, bucketName);
            case PROD:
                return new AmazonS3StorageRepoImpl(amazonS3, bucketName);
            default:
                return new AmazonS3StorageRepoImpl(amazonS3, bucketName);

        }
    }
}
