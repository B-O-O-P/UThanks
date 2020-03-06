package com.uthanks;

import com.uthanks.domain.Role;
import com.uthanks.domain.User;
import com.uthanks.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * This is not real test class, now it's only for representing code structure.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByName_thenReturnUser() {
        User polinb = new User();
        polinb.setLogin("PolinB");
        polinb.setEmail("Polin@sa");
        entityManager.persist(polinb);
        entityManager.flush();

        User found = userRepository.findByLogin(polinb.getLogin());

        assertThat(found.getLogin(), is(polinb.getLogin()));
    }
}
