package com.uthanks.services;

// import com.uthanks.repository.UserRepository;
import com.uthanks.domain.User;
import com.uthanks.form.UserCredentials;
import com.uthanks.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * Class for working with userRepository.
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isLoginVacant(String login) {
        return userRepository.countByLogin(login) == 0;
    }

    public User register(UserCredentials registerForm) {
        User user = new User();
        user.setLogin(registerForm.getLogin());
        user.setEmail(registerForm.getEmail());
        userRepository.save(user);
        userRepository.updatePasswordSha(user.getId(), registerForm.getPassword());
        return user;
    }

    public User findByLoginAndPassword(String login, String password) {
        return login == null || password == null ? null : userRepository.findByLoginAndPassword(login, password);
    }
}