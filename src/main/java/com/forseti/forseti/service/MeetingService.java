package com.forseti.forseti.service;

import com.forseti.forseti.model.Meeting;
import com.forseti.forseti.model.MeetingStatus;
import com.forseti.forseti.repository.MeetingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MeetingService {

    private MeetingRepository meetingRepository;

    public List<Meeting> doneUserMeetings(String userId) {
        return getUserWithStatus(userId, meetingStatus -> meetingStatus.equals(MeetingStatus.FINISHED));
    }

    public List<Meeting> notDoneUserMeetings(String userId) {
        return getUserWithStatus(userId, meetingStatus -> !meetingStatus.equals(MeetingStatus.FINISHED));
    }

    private List<Meeting> getUserWithStatus(String userId, Predicate<MeetingStatus> meetingStatusPredicate) {
        return meetingRepository.findAll().stream()
                .filter(meeting -> userIsInMeeting(userId, meeting) && meetingStatusPredicate.test(meeting.getStatus()))
                .sorted(Comparator.comparing(Meeting::getDate))
                .collect(Collectors.toList());
    }

    private boolean userIsInMeeting(String userId, Meeting meeting) {
        return meeting.getRequests().stream().anyMatch(request -> request.user.equals(userId));
    }
}
