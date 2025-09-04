package com.gtelant.commerce_servise.mappers;

import com.gtelant.commerce_servise.dtos.UserRequest;
import com.gtelant.commerce_servise.dtos.UserResponse;
import com.gtelant.commerce_servise.model.Segment;
import com.gtelant.commerce_servise.model.User;
import com.gtelant.commerce_servise.model.UserSegment;
import com.gtelant.commerce_servise.repositories.SegmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;
    private final SegmentRepository segmentRepository;

    public UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setAddress(user.getAddress());
        response.setBirthday(user.getBirthday());
        response.setCity(user.getCity());
        response.setState(user.getState());
        response.setZipcode(user.getZipcode());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());
        response.setHasNewsletter(user.isHasNewsletter());
        List<Integer> segmentIds =
                (user.getUserSegments() == null) ? List.of() :
                        user.getUserSegments().stream()
                                .filter(Objects::nonNull)
                                .map(UserSegment::getSegment)
                                .filter(Objects::nonNull)
                                .map(Segment::getId)
                                .filter(Objects::nonNull)
                                .distinct()
                                .toList();
        response.setSegmentIds(segmentIds);
        return response;
    }

    public User toEntity(UserRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setBirthday(request.getBirthday());
        user.setAddress(request.getAddress());
        user.setCity(request.getCity());
        user.setState(request.getState());
        user.setZipcode(request.getZipcode());
        user.setPassword(request.getPassword());
        user.setRole("ROLE_USER");
        user.setHasNewsletter(request.getHasNewsletter());
        if (request.getSegmentIds() != null && !request.getSegmentIds().isEmpty()) {
            Set<UserSegment> links = request.getSegmentIds().stream()
                    .filter(Objects::nonNull)
                    .distinct()
                    .map(segId -> {
                        Segment segRef = new Segment();
                        segRef.setId(segId);
                        UserSegment link = new UserSegment();
                        link.setUser(user);
                        link.setSegment(segRef);
                        return link;
                    })
                    .collect(Collectors.toSet());
            user.setUserSegments(links);
        }
        return user;
    }

    public User updateEntity(User user, UserRequest request) {
        if (StringUtils.hasText(request.getFirstName())) user.setFirstName(request.getFirstName());
        if (StringUtils.hasText(request.getLastName())) user.setLastName(request.getLastName());
        if (StringUtils.hasText(request.getEmail())) user.setEmail(request.getEmail());
        if (request.getBirthday() != null) user.setBirthday(request.getBirthday());
        if (StringUtils.hasText(request.getAddress())) user.setAddress(request.getAddress());
        if (StringUtils.hasText(request.getCity())) user.setCity(request.getCity());
        if (StringUtils.hasText(request.getState())) user.setState(request.getState());
        if (StringUtils.hasText(request.getZipcode())) user.setZipcode(request.getZipcode());
        if (StringUtils.hasText(request.getPassword())) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        // 只有在前端真的有帶 hasNewsletter 時才更新，避免把原值誤蓋掉
        if (request.getHasNewsletter() != null) {
            user.setHasNewsletter(request.getHasNewsletter());
        }
        // 若你有要同時更新 Segment 關聯（ManyToMany 或中介表）
        if (request.getSegmentIds() != null) {
            // ManyToMany 版本（若 User 有 `Set<Segment> segments`）：
            // Set<Segment> segs = new HashSet<>(segmentRepository.findAllById(request.getSegmentIds()));
            // user.setSegments(segs);
            // 若你使用 UserSegment 中介表，請在 Service 層處理新增/刪除 UserSegment，避免在 mapper 內做複雜邏輯。
        }
        return user;
    }
}


