package com.gtelant.commerce_servise.mappers;

import com.gtelant.commerce_servise.dtos.UserSegmentResponse;
import com.gtelant.commerce_servise.model.Segment;
import com.gtelant.commerce_servise.model.User;
import com.gtelant.commerce_servise.model.UserSegment;
import org.springframework.stereotype.Component;

@Component
public class UserSegmentMapper {
    public UserSegmentResponse toResponse(UserSegment userSegment) {
        UserSegmentResponse response = new UserSegmentResponse();
        response.setId(userSegment.getId());
        response.setUserId(userSegment.getUser().getId());
        response.setSegmentId(userSegment.getId());
        response.setName(userSegment.getSegment().getName());
        return response;
    }

    public UserSegment toEntity(User user, Segment segment) {
        UserSegment userSegment = new UserSegment();
        userSegment.setUser(user);
        userSegment.setSegment(segment);
        return userSegment;
    }
}
