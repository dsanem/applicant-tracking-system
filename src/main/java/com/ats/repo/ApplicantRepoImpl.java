package com.ats.repo;

import com.ats.model.Applicant;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ApplicantRepoImpl implements ApplicantRepository {

    private final ReactiveMongoTemplate mongoTemplate;

    @Autowired
    public ApplicantRepoImpl(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Mono<Applicant> insert(Applicant applicant) {
        return mongoTemplate.insert(applicant);
    }

    @Override
    public Flux<Applicant> findApplicantByFirstName(String firstName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").is(firstName));
        return mongoTemplate.find(query, Applicant.class);
    }

    @Override
    public Flux<Applicant> findApplicantByLastName(String lastName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("lastName").is(lastName));
        return mongoTemplate.find(query, Applicant.class);
    }

    @Override
    public Mono<Applicant> findApplicantById(String id) {
        return mongoTemplate.findById(id, Applicant.class);
    }

    @Override
    public Flux<Applicant> findApplicantByPhoneNumber(String phoneNumber) {
        Query query = new Query();
        query.addCriteria(Criteria.where("phoneNumber").is(phoneNumber));
        return mongoTemplate.find(query, Applicant.class);
    }

    @Override
    public Mono<UpdateResult> update(Applicant applicant, String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("firstName", applicant.getFirstName());
        update.set("lastName", applicant.getLastName());
        update.set("phoneNumber", applicant.getPhoneNumber());
        return mongoTemplate.updateFirst(query, update, Applicant.class);
    }
}
