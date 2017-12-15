package com.ats.service;

import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AmazonS3Service {

    public Flux<Integer> saveFile(MultipartFile file);

    public Mono<MultipartFile> findFileWithId(String id);
}
