package com.forseti.forseti.controller;

import com.forseti.forseti.model.Meeting;
import com.forseti.forseti.service.MeetingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class MeetingController {
    private MeetingService meetingService;

    @RequestMapping(value = "/api/doneUserMeetings", method = RequestMethod.GET)
    public List<Meeting> doneUserMeetings(String userId) {
        return meetingService.doneUserMeetings(userId);
    }

    @RequestMapping(value = "/api/notDoneUserMeetings", method = RequestMethod.GET)
    public List<Meeting> notDoneUserMeetings(String userId) {
        return meetingService.notDoneUserMeetings(userId);
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
