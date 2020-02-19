package com.uthanks.repository;

import com.uthanks.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Class for working with users data.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLoginAndPassword(String login, String password);
}
