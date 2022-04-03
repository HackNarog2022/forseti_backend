package com.forseti.forseti.service;

import com.forseti.forseti.model.Meeting;
import com.forseti.forseti.model.MeetingStatus;
import com.forseti.forseti.model.Request;
import com.forseti.forseti.repository.MeetingRepository;
import com.forseti.forseti.repository.RequestRepository;
import com.forseti.forseti.repository.SuggestedPlaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Component
@AllArgsConstructor
public class MatchingService {
    private RequestRepository requestRepository;
    private MeetingRepository meetingRepository;
    private SuggestedPlaceService suggestedPlaceService;

    public void matchRequest(Request request) {
        List<Request> all = requestRepository.findAll();
        List<Request> requestList = all
                .stream()
                .filter(candidate -> !candidate.getRequestId().equals(request.getRequestId()))
                .filter(candidate -> !candidate.isNegative())
                .filter(candidate -> !candidate.getUser().getId().equals(request.getUser().getId()))
                .filter(candidate -> candidate.getCategory().getName().equals(request.getCategory().getName()))
                .filter(candidate -> candidate.getStartDate().before(request.getEndDate()))
                .filter(candidate -> candidate.getEndDate().after(request.getStartDate()))
                .filter(candidate -> candidate.getPlace().equals(request.getPlace()))
                .filter(candidate -> candidate.getDeclaredExpertise().equals(request.getExpectedExpertise()))
                .filter(candidate -> candidate.getExpectedExpertise().equals(request.getDeclaredExpertise()))
                .toList();

        if (requestList.size() > 0) {
            Request matched = requestList.get(new Random().nextInt(requestList.size()));
            Meeting meeting = Meeting.builder()
                    .requests(Set.of(request, matched))
                    .category(request.getCategory())
                    .date(new Date(Calendar.getInstance().getTime().getTime()))
                    .inspirations(request.getCategory().getInspirations())
                    .status(MeetingStatus.NOT_YET_MET)
                    .suggestedPlaces(suggestedPlaceService.getSuggestedPlacesForLocation(request.getPlace()))
                    .build();

            meetingRepository.insert(meeting);
            requestRepository.delete(matched);
            requestRepository.delete(request);
        }
    }

}
