package com.ats;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


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
}
