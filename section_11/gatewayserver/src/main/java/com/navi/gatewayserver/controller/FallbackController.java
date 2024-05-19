package com.navi.gatewayserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @GetMapping("/contactSupport")
    public Mono<String>  contactSupport(){
        return Mono.just("Please try after some time, if the same issue exists, please contact support team ....!");
    }
}
