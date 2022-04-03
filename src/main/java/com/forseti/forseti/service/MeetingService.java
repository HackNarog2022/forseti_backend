package com.forseti.forseti.service;

import com.forseti.forseti.model.Meeting;
import com.forseti.forseti.repository.MeetingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MeetingService {

    private MeetingRepository meetingRepository;

    public Meeting meeting(String id) {
        return meetingRepository.findById(id).orElseThrow();
    }

    public List<Meeting> doneUserMeetings(String userId) {
        return getUserWithStatus(userId, false);
    }

    public List<Meeting> notDoneUserMeetings(String userId) {
        return getUserWithStatus(userId, true);
    }

    public List<Integer> getUserRatings(String userId) {
        return meetingRepository.findAll().stream()
                .sorted(Comparator.comparing(Meeting::getDate))
                .map(meeting -> meeting.getRatings().get(userId))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * @param meetingId
     * @param userId    id of user that gets a rating
     * @param rating
     */
    public Optional<Meeting> addRating(String meetingId, String userId, Integer rating) {
        return meetingRepository.findById(meetingId).map(foundMeeting -> {
            if(foundMeeting.getRatings() == null) {
                foundMeeting.setRatings( new HashMap<>());
            }
            foundMeeting.getRatings().put(userId, rating);
            meetingRepository.save(foundMeeting);
            return foundMeeting;
        });
    }

    public Optional<Meeting> setFinished(String meetingId) {
        return meetingRepository.findById(meetingId).map(foundMeeting -> {
            foundMeeting.setActive(false);
            meetingRepository.save(foundMeeting);
            return foundMeeting;
        });
    }

    private List<Meeting> getUserWithStatus(String userId, boolean isActive) {
        return meetingRepository.findAll().stream()
                .filter(meeting -> userIsInMeeting(userId, meeting))
                .filter(meeting -> meeting.isActive() == isActive)
                .sorted(Comparator.comparing(Meeting::getDate))
                .collect(Collectors.toList());
    }

    private boolean userIsInMeeting(String userId, Meeting meeting) {
        return meeting.getRequests().stream().anyMatch(request -> request.getUser().getId().equals(userId));
    }
}
