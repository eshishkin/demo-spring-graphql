package org.eshishkin.edu.demographql.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eshishkin.edu.demographql.exception.UserAlreadyExistException;
import org.eshishkin.edu.demographql.model.CustomerRequest;
import org.eshishkin.edu.demographql.persistence.model.UserEntity;
import org.eshishkin.edu.demographql.persistence.repository.UserRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CustomerController {
    private final UserRepository userRepository;

    @Transactional
    @MutationMapping(name = "customer")
    public UserEntity createUser(@Argument CustomerRequest request) {
        userRepository.findByEmail(request.getEmail()).ifPresent(user -> {
            throw new UserAlreadyExistException("User already exist in the database: " + user.getEmail());
        });

        var user = new UserEntity();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        return userRepository.save(user);
    }
}
