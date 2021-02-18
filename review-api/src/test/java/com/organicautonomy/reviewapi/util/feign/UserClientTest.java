package com.organicautonomy.reviewapi.util.feign;

import com.organicautonomy.reviewapi.dto.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserClientTest {
    private final User TO_SAVE = new User("brooke", "brooke@gmail.com");
    private final User USER1 = new User(1, "brooke", "brooke@gmail.com");
    private final User USER2 = new User(2, "$GMEtothemoon", "bagholder@gmail.com");

    @MockBean
    private UserClient client;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateUser() {
        when(client.createUser(TO_SAVE)).thenReturn(USER1);

        User user = client.createUser(TO_SAVE);

        assertEquals(USER1, user);
    }

    @Test
    void testGetAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(USER1);
        users.add(USER2);

        when(client.getAllUsers()).thenReturn(users);

        List<User> fromClient = client.getAllUsers();

        assertEquals(2, fromClient.size());
    }

    @Test
    void testGetUserById() {
        when(client.getUserById(USER1.getId())).thenReturn(USER1);

        User fromClient = client.getUserById(USER1.getId());
        assertEquals(USER1, fromClient);
    }

    @Test
    void testUpdateUser() {
        User user = new User(1, "brooke", "brooke@gmail.com");
        user.setUsername("brookGME");

        doNothing().when(client).updateUser(USER1.getId(), user);

        client.updateUser(user.getId(), user);
    }

    @Test
    void testDeleteUser() {
        doNothing().when(client).deleteUser(USER2.getId());

        client.deleteUser(USER2.getId());
    }

    @Test
    void testGetUserByUsername() {
        when(client.getUserByUsername(USER2.getUsername())).thenReturn(USER2);

        User user = client.getUserByUsername(USER2.getUsername());

        assertEquals(USER2, user);
    }

    @Test
    void getUserByEmail() {
        when(client.getUserByEmail(USER1.getEmail())).thenReturn(USER1);

        User user = client.getUserByEmail(USER1.getEmail());

        assertEquals(USER1, user);
    }
}