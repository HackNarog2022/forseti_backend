package com.forseti.forseti.controller;

import com.azure.spring.cloud.autoconfigure.aad.implementation.oauth2.AadOAuth2AuthenticatedPrincipal;
import com.forseti.forseti.model.Request;
import com.forseti.forseti.service.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
public class RequestController {
    private final RequestService requestService;

    @PostMapping(path = "/api/requests", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Request> addRequest(@RequestBody Request request) {
        var newRequest = requestService.addRequest(request);
        if (newRequest == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(newRequest, HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "/api/requests", method = RequestMethod.GET)
    public List<Request> allRequests() {
        var user = (AadOAuth2AuthenticatedPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return requestService.getAllUserRequests(user.getName());
    }

    @RequestMapping(value = "/api/request/{id}", method = RequestMethod.GET)
    public Request request(@PathVariable("id") String id) {
        return requestService.getById(id);
    }
}
