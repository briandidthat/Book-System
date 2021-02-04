package com.organicautonomy.userservice.dao;

import com.organicautonomy.userservice.dto.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserRepositoryTest {
    @Autowired
    private UserRepository repository;
    private User user1, user2;

    @BeforeEach
    void setUp() {
        repository.deleteAll();

        user1 = new User("brooke", "brooke@gmail.com");
        user2 = new User("$GMEtothemoon", "bagholder@gmail.com");
    }

    @Test
    void saveFindUser() {
        user1 = repository.save(user1);
        Optional<User> fromRepository = repository.findById(user1.getId());

        assertEquals(user1, fromRepository.get());
    }

    @Test
    void findUserByUsername() {
        user1 = repository.save(user1);
        User fromRepository = repository.findUserByUsername(user1.getUsername());

        assertEquals(user1, fromRepository);
    }

    @Test
    void findUserByEmail() {
        user2 = repository.save(user2);
        User fromRepository = repository.findUserByEmail(user2.getEmail());

        assertEquals(user2, fromRepository);
    }

    @Test
    void findAllUsers() {
        user1 = repository.save(user1);
        user2 = repository.save(user2);

        List<User> users = repository.findAll();

        assertEquals(2, users.size());
    }
}