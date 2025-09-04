package com.gtelant.commerce_servise.services;

import com.gtelant.commerce_servise.model.User;
import com.gtelant.commerce_servise.model.UserSegment;
import com.gtelant.commerce_servise.repositories.UserRepository;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Page<User> getAllUsers(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest);
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public Page<User> getAllUsers(String query, Boolean hasNewsletter, Integer segmentId, PageRequest pageRequest) {
        Specification<User> spec = userSpecification(query, hasNewsletter, segmentId);
        return userRepository.findAll(spec, pageRequest);
    }

    public Page<User> searchUsersByName(String raw, Pageable pageable) {
        Specification<User> spec = userSpecification(raw, null, null);
        return userRepository.findAll(spec, pageable);
    }

    private Specification<User> userSpecification(String queryName, Boolean hasNewsletter, Integer segmentId) {
        return (Root<User> root, CriteriaQuery<?> cq, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (queryName != null && !queryName.isBlank()) {
                String q = queryName.trim().replaceAll("\\s+", " ");
                String qLower = q.toLowerCase();
                String qNo = qLower.replace(" ", "");
                Expression<String> fn_ln = cb.lower(cb.concat(cb.concat(root.get("firstName"), " "), root.get("lastName")));
                Expression<String> ln_fn = cb.lower(cb.concat(cb.concat(root.get("lastName"), " "), root.get("firstName")));
                Expression<String> fnln = cb.lower(cb.concat(root.get("firstName"), root.get("lastName")));
                Expression<String> lnfn = cb.lower(cb.concat(root.get("lastName"), root.get("firstName")));
                predicates.add(cb.or(
                        cb.like(fn_ln, "%" + qLower + "%"),
                        cb.like(ln_fn, "%" + qLower + "%"),
                        cb.like(fnln, "%" + qNo + "%"),
                        cb.like(lnfn, "%" + qNo + "%")
                ));
            }
            if (hasNewsletter != null) {
                predicates.add(cb.equal(root.get("hasNewsletter"), hasNewsletter));
            }
            if (segmentId != null) {
                Subquery<Integer> sub = cq.subquery(Integer.class);
                Root<UserSegment> us = sub.from(UserSegment.class);
                sub.select(cb.literal(1))
                        .where(
                                cb.equal(us.get("user"), root),
                                cb.equal(us.get("segment").get("id"), segmentId)
                        );
                predicates.add(cb.exists(sub));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}








