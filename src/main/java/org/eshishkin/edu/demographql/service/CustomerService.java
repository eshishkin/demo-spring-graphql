package org.eshishkin.edu.demographql.service;

import lombok.RequiredArgsConstructor;
import org.eshishkin.edu.demographql.exception.UserAlreadyExistException;
import org.eshishkin.edu.demographql.model.CustomerRequest;
import org.eshishkin.edu.demographql.persistence.model.UserEntity;
import org.eshishkin.edu.demographql.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final UserRepository userRepository;

    public Mono<UserEntity> create(CustomerRequest request) {
        return Mono.fromSupplier(() -> {
            userRepository.findByEmail(request.getEmail()).ifPresent(user -> {
                throw new UserAlreadyExistException("User already exist in the database: " + user.getEmail());
            });

            var user = new UserEntity();
            user.setEmail(request.getEmail());
            user.setName(request.getName());
            return userRepository.save(user);
        });
    }
}
