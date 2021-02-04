package com.organicautonomy.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.organicautonomy.userservice.dao.UserRepository;
import com.organicautonomy.userservice.dto.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {
    private final User TO_SAVE = new User("brooke", "brooke@gmail.com");
    private final User USER1 = new User(1, "brooke", "brooke@gmail.com");
    private final User USER2 = new User(2, "$GMEtothemoon", "bagholder@gmail.com");

    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserRepository repository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetAllUsers() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(USER1);
        users.add(USER2);

        String outputJson = mapper.writeValueAsString(users);

        when(repository.findAll()).thenReturn(users);

        this.mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson))
                .andDo(print());
    }

    @Test
    void testCreateUser() throws Exception {
        String inputJson = mapper.writeValueAsString(TO_SAVE);
        String outputJson = mapper.writeValueAsString(USER1);

        when(repository.save(TO_SAVE)).thenReturn(USER1);

        this.mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson))
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    void testGetUserByUsername() throws Exception {
        String outputJson = mapper.writeValueAsString(USER2);

        when(repository.findUserByUsername(USER2.getUsername())).thenReturn(USER2);

        this.mockMvc.perform(get("/users/username/" + USER2.getUsername()))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson))
                .andDo(print());
    }
}