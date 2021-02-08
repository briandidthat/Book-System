package com.organicautonomy.userservice.controller;

import com.organicautonomy.userservice.dao.UserRepository;
import com.organicautonomy.userservice.dto.User;
import com.organicautonomy.userservice.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository repository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody @Valid User user) {
        return repository.save(user);
    }

    @GetMapping("/username/{username}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserByUsername(@PathVariable String username) {
        User user = repository.findUserByUsername(username);

        if (user == null) {
            throw new ResourceNotFoundException("There is no user associated with the username provided.");
        }

        return user;
    }

    @GetMapping("/email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserByEmail(@PathVariable String email) {
        User user = repository.findUserByEmail(email);

        if (user == null) {
            throw new ResourceNotFoundException("There is no user associated with the email provided.");
        }

        return user;
    }
}
