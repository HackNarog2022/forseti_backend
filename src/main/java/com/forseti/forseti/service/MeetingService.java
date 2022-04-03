package com.forseti.forseti.service;

import com.forseti.forseti.model.Meeting;
import com.forseti.forseti.model.MeetingStatus;
import com.forseti.forseti.repository.MeetingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MeetingService {

    private MeetingRepository meetingRepository;

    public Meeting meeting(String id) {
        return meetingRepository.findById(id).orElseThrow();
    }

    public List<Meeting> doneUserMeetings(String userId) {
        return getUserWithStatus(userId, meetingStatus -> meetingStatus != null && meetingStatus.equals(MeetingStatus.FINISHED));
    }

    public List<Meeting> notDoneUserMeetings(String userId) {
        return getUserWithStatus(userId, meetingStatus -> meetingStatus == null || !meetingStatus.equals(MeetingStatus.FINISHED));
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
        var meeting = meetingRepository.findById(meetingId);
        return meeting.map(foundMeeting -> {
            foundMeeting.getRatings().put(userId, rating);
            meetingRepository.save(foundMeeting);
            return foundMeeting;
        });
    }

    private List<Meeting> getUserWithStatus(String userId, Predicate<MeetingStatus> meetingStatusPredicate) {
        return meetingRepository.findAll().stream()
                .filter(meeting -> userIsInMeeting(userId, meeting))
                .filter(meeting -> meetingStatusPredicate.test(meeting.getStatus()))
                .sorted(Comparator.comparing(Meeting::getDate))
                .collect(Collectors.toList());
    }

    private boolean userIsInMeeting(String userId, Meeting meeting) {
        return meeting.getRequests().stream().anyMatch(request -> request.getUser().getId().equals(userId));
    }
}
