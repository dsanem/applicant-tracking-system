package com.ats;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;


/**
 * Created by sanemdeepak on 12/16/17.
 */
@Component
public class ATSBeans {

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();

    }

    @Bean
    @Qualifier("ATSValidator")
    public Validator getValidator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class).configure().buildValidatorFactory();
        return validatorFactory.getValidator();

    }

    @Bean
    @Qualifier("googleCredentials")
    public GoogleCredentials getGoogleCredentials() throws IOException {
        return GoogleCredentials.getApplicationDefault();
    }

    @Bean
    public Storage getStorage(@Qualifier("googleCredentials") GoogleCredentials credentials) {
        return StorageOptions.newBuilder().setCredentials(credentials).build().getService();
    }
}
