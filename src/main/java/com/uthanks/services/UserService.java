package com.uthanks.services;

import com.uthanks.domain.Role;
import com.uthanks.domain.User;
import com.uthanks.form.UserCredentials;
import com.uthanks.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class for working with userRepository.
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    private final Role VOLUNTEER_ROLE = new Role(1, Role.RoleName.VOLUNTEER);
    private final Role ORGANIZATION_ROLE = new Role(2, Role.RoleName.ORGANIZATION);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isLoginVacant(String login) {
        return userRepository.countByLogin(login) == 0;
    }

    public User register(UserCredentials registerForm) {
        User user = new User();
        user.setLogin(registerForm.getLogin());
        user.setName(registerForm.getName());
        user.setEmail(registerForm.getEmail());
        switch (registerForm.getUserType()) {
            case VOLUNTEER:
                user.setRole(VOLUNTEER_ROLE);
                break;
            case ORGANIZATION:
                user.setRole(ORGANIZATION_ROLE);
                break;
            default:
                throw new IllegalArgumentException("Unmapped role");
        }
        userRepository.save(user);
        userRepository.updatePasswordSha(user.getId(), registerForm.getPassword());
        return user;
    }

    public User findByLoginAndPassword(String login, String password) {
        return login == null || password == null ? null : userRepository.findByLoginAndPassword(login, password);
    }

    public List<User> findOrganizations() {
        return userRepository.findByRoleId(ORGANIZATION_ROLE.getId());
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("No user with id=%d found", id)));
    }

    public User saveAdditionalInfo(Long id, int age, String country, String fullName, String skills) {
        User user = findById(id);
        user.setAge(age);
        user.setCountry(country);
        user.setFullName(fullName);
        user.setSkills(skills);
        userRepository.save(user);
        return user;
    }
}