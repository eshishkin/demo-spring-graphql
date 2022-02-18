package org.eshishkin.edu.demographql.persistence.repository;

import org.eshishkin.edu.demographql.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}