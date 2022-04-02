package com.forseti.forseti.controller;

import com.azure.spring.cloud.autoconfigure.aad.implementation.oauth2.AadOAuth2AuthenticatedPrincipal;
import com.forseti.forseti.model.Category;
import com.forseti.forseti.model.Inspiration;
import com.forseti.forseti.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@AllArgsConstructor
public class UserController {

    @RequestMapping(value = "/api/user", method = RequestMethod.GET)
    public Map<String, Object> getUser() {
        var user = (AadOAuth2AuthenticatedPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getAttributes();
    }

}
