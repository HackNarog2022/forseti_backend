package com.forseti.forseti.controller;

import com.forseti.forseti.model.Meeting;
import com.forseti.forseti.service.MeetingService;
import lombok.AllArgsConstructor;
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
}
