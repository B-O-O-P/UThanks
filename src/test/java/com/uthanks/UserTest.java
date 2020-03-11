package com.uthanks;

import com.uthanks.domain.User;
import com.uthanks.repository.UserRepository;
import com.uthanks.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindUserByLoginAndPassword() {
        UserService userService = new UserService(userRepository);
        String passwordSha = ReflectionTestUtils.invokeMethod(userService, "getHash", "1234");
        User polinb = new User();
        polinb.setLogin("PolinB");
        polinb.setEmail("Polin@sa");
        polinb.setPasswordSha(passwordSha);
        polinb = entityManager.persistAndFlush(polinb);

        User found = userRepository.findByLoginAndPasswordSha(polinb.getLogin(), passwordSha);

        assertThat(found.getLogin(), is(polinb.getLogin()));
    }
}
