package com.organicautonomy.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.organicautonomy.userservice.dao.UserRepository;
import com.organicautonomy.userservice.dto.User;
import com.organicautonomy.userservice.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    private final User INVALID = new User();

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
    void testCreateUserWithInvalidUserFormat() throws Exception {
        String inputJson = mapper.writeValueAsString(INVALID);

        this.mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andDo(print());
    }

    @Test
    void testGetUserById() throws Exception {
        String outputJson = mapper.writeValueAsString(USER2);
        when(repository.findById(USER2.getId())).thenReturn(Optional.of(USER2));

        this.mockMvc.perform(get("/users/{userId}", USER2.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson))
                .andDo(print());
    }

    @Test
    void testGetUserByIdWithInvalidId() throws Exception {
        when(repository.findById(USER2.getId())).thenReturn(Optional.empty());

        this.mockMvc.perform(get("/users/{userId}", USER2.getId()))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result -> assertEquals("There is no user associated with the id provided.",
                        result.getResolvedException().getMessage()))
                .andDo(print());
    }

    @Test
    void testUpdateUser() throws Exception {
        String inputJson = mapper.writeValueAsString(USER1);
        when(repository.findById(USER1.getId())).thenReturn(Optional.of(USER1));

        this.mockMvc.perform(put("/users/{userId}", USER1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson))
                .andExpect(status().isNoContent())
                .andExpect(content().string(""))
                .andDo(print());
    }


    @Test
    void testGetUserByUsername() throws Exception {
        String outputJson = mapper.writeValueAsString(USER2);

        when(repository.findUserByUsername(USER2.getUsername())).thenReturn(USER2);

        this.mockMvc.perform(get("/users/username/{username}", USER2.getUsername()))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson))
                .andDo(print());
    }

    @Test
    void testGetUserByUsernameWithInvalidUsername() throws Exception {
        String invalid = "INVALID";
        when(repository.findUserByUsername(invalid)).thenReturn(null);

        this.mockMvc.perform(get("/users/username/{username}", invalid))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result -> assertEquals("There is no user associated with the username provided.",
                        result.getResolvedException().getMessage()))
                .andDo(print());
    }

    @Test
    void testGetUserByEmail() throws Exception {
        String outputJson = mapper.writeValueAsString(USER2);

        when(repository.findUserByEmail(USER2.getEmail())).thenReturn(USER2);

        this.mockMvc.perform(get("/users/email/{email}", USER2.getEmail()))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson))
                .andDo(print());
    }

    @Test
    void testGetUserByEmailWithInvalidEmail() throws Exception {
        String invalid = "INVALID@GMAIL.COM";

        when(repository.findUserByEmail(invalid)).thenReturn(null);

        this.mockMvc.perform(get("/users/email/{email}", invalid))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result -> assertEquals("There is no user associated with the email provided.",
                        result.getResolvedException().getMessage()))
                .andDo(print());
    }
}