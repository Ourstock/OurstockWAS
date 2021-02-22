package com.ourstock.api.ourstock_api.controller;

import com.ourstock.api.ourstock_api.repository.ExampleRepository;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    private final ExampleRepository exampleRepository;

    public ExampleController(ExampleRepository exampleRepository) {
        this.exampleRepository = exampleRepository;
    }

    @GetMapping(value = "/example")
    String get() {
        return "Example";
    }

    @PostMapping(value = "/eample/register")
    String get(@PathVariable Integer phoneNumber) {
        return phoneNumber.toString();
    }

    @GetMapping(value = "/example/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    String get(@PathVariable Long id) {
        return id.toString();
        // return "Example!";
    }
}
