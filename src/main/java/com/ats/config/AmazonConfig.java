package com.ats.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {

    private final String AWS_ACCESS_KEY_ID;
    private final String AWS_SECRET_KEY;

    public AmazonConfig(@Value("${aws.accessKeyId}") String aws_access_key_id,
                        @Value("${aws.secretKey}") String aws_secret_key) {
        AWS_ACCESS_KEY_ID = aws_access_key_id;
        AWS_SECRET_KEY = aws_secret_key;
    }

    @Bean
    public AmazonS3 getAmazonS3Client() {

        AmazonS3 s3 = AmazonS3Client.builder()
                .withRegion("us-east-1")
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(AWS_ACCESS_KEY_ID, AWS_SECRET_KEY)))
                .build();
        return s3;
    }
}
