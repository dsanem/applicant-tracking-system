package com.ats.repo;

import com.ats.model.Applicant;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by sanemdeepak on 4/13/18.
 */

@Repository
public class LocalStorageRepoImpl implements ResumeStorageRepo {
    @Override
    public Mono<Long> saveResume(MultipartFile file, Applicant applicant) throws IOException {
        String userHomeFolder = System.getProperty("user.home");
        userHomeFolder = userHomeFolder.concat("/Desktop");
        File textFile = new File(userHomeFolder, applicant.getLastName());

        FileOutputStream outputStream = new FileOutputStream(textFile);
        outputStream.write(file.getBytes());
        outputStream.flush();
        outputStream.close();

        return Mono.just(file.getSize());
    }

    @Override
    public Flux<Object> findResumeWithId(String id) {
        return null;
    }
}
