package com.dominikpalichleb.trainingapp.service;

import com.dominikpalichleb.trainingapp.domain.dto.UserDto;
import com.dominikpalichleb.trainingapp.domain.model.User;
import com.dominikpalichleb.trainingapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class RegistrationServiceTest {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private UserService userService;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldAddUserToDatabase() throws Exception {
        // given
        final String EMAIL = "test@gmail.com";
        final String PASSWORD = "test-password";
        final String USERNAME = "test-username";

        UserDto userDto = createUser(EMAIL, USERNAME, PASSWORD);

        when(bCryptPasswordEncoder.encode(PASSWORD)).thenReturn(PASSWORD);

        // when
        registrationService.register(userDto);

        //then
        User user = userRepository.findByUsername(USERNAME).orElseThrow(() -> new IllegalStateException("User not found"));

        assertNotNull(user);
        assertEquals(EMAIL, user.getEmail());
        assertEquals(PASSWORD, user.getPassword());
        assertEquals(USERNAME, user.getUsername());

    }

    private static UserDto createUser(String email, String username, String password) {
        return UserDto.builder()
                .email(email)
                .username(username)
                .password(password)
                .build();
    }

}