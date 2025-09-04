package com.gtelant.commerce_servise.services;

import com.gtelant.commerce_servise.model.UserSegment;
import com.gtelant.commerce_servise.repositories.UserSegmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSegmentService {
    private final UserSegmentRepository userSegmentRepository;

    public UserSegmentService(UserSegmentRepository userSegmentRepository) {
        this.userSegmentRepository = userSegmentRepository;
    }

    public List<UserSegment> getAllUserSegments() {
        return userSegmentRepository.findAll();
    }

    public UserSegment saveUserSegment(UserSegment userSegment) {
        return userSegmentRepository.save(userSegment);
    }
}
