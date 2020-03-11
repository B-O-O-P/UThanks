package com.uthanks;

import com.uthanks.domain.Role;
import com.uthanks.domain.User;
import com.uthanks.form.UserCredentials;
import com.uthanks.repository.UserRepository;
import com.uthanks.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    public void user_register_test() {
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setLogin("PolinB");
        userCredentials.setEmail("Polina@sd.re");
        userCredentials.setPassword("1234");
        userCredentials.setUserType(Role.RoleName.VOLUNTEER);

        when(userRepository.save(any(User.class))).thenReturn(new User());

        User created = userService.register(userCredentials);

        assertThat(created.getLogin()).isSameAs(userCredentials.getLogin());
    }
}
