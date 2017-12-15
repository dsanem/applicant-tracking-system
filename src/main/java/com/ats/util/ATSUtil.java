package com.ats.util;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;


public class ATSUtil {

    private final Scheduler scheduler;

    public ATSUtil(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public <T> Mono<T> asyncMono(Callable<T> callable) {
        return Mono.fromCallable(callable).publishOn(scheduler);
    }

    public <T> Flux<T> asyncFlux(List<Callable> callable) {
        List<Mono<T>> monoList = new ArrayList<>();
        callable.forEach(call -> monoList.add(asyncMono(call)));
        Mono<T> first = monoList.get(1);
        monoList.forEach(first::concatWith);
        return first.flux();
    }


    public static String buildS3PathWith(String lastName, String id) {

        return lastName.concat("/").concat(id);
    }
}
