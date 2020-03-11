package com.uthanks.repository;

import com.uthanks.domain.Role;
import com.uthanks.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class for working with users data.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    int countByLogin(String login);

    @Query(value = "SELECT * FROM user WHERE login=?1 AND password_sha=?2", nativeQuery = true)
    User findByLoginAndPasswordSha(String login, String passwordSha);

    @Query(value = "SELECT * FROM user WHERE role_id=?1", nativeQuery = true)
    List<User> findByRoleId(long roleId);

    @Query(value = "SELECT * FROM user WHERE login=?1", nativeQuery = true)
    User findByLogin(String login);
}