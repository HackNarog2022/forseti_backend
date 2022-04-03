package com.forseti.forseti.controller;

import com.azure.spring.cloud.autoconfigure.aad.implementation.oauth2.AadOAuth2AuthenticatedPrincipal;
import com.forseti.forseti.model.Meeting;
import com.forseti.forseti.service.MeetingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class MeetingController {
    private MeetingService meetingService;

    @RequestMapping(value = "/api/meeting/{id}", method = RequestMethod.GET)
    public Meeting meeting(@PathVariable("id") String id) {
        return meetingService.meeting(id);
    }

    @RequestMapping(value = "/api/doneUserMeetings", method = RequestMethod.GET)
    public List<Meeting> doneUserMeetings() {
        var user = (AadOAuth2AuthenticatedPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return meetingService.doneUserMeetings(user.getName());
    }

    @RequestMapping(value = "/api/notDoneUserMeetings", method = RequestMethod.GET)
    public List<Meeting> notDoneUserMeetings() {
        var user = (AadOAuth2AuthenticatedPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return meetingService.notDoneUserMeetings(user.getName());
    }

    @PostMapping(path = "/api/setFinished", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meeting> setFinished(String meetingId) {
        return meetingService.setFinished(meetingId)
                .map(meeting -> new ResponseEntity<>(meeting, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PostMapping(path = "/api/addRating", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meeting> addRating(String meetingId, String userId, Integer rating) {
        return meetingService.addRating(meetingId, userId, rating)
                .map(meeting -> new ResponseEntity<>(meeting, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/api/userRatings", method = RequestMethod.GET)
    public List<Integer> getUserRatings(String userId) {
        return meetingService.getUserRatings(userId);
    }
}
