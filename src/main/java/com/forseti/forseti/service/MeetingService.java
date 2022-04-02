package com.forseti.forseti.service;

import com.forseti.forseti.repository.MeetingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MeetingService {

    private MeetingRepository meetingRepository;
}
