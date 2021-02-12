package com.organicautonomy.reviewapi.util.feign;

import com.organicautonomy.reviewapi.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.List;

@FeignClient(name = "user-service")
@RequestMapping(value = "/users")
public interface UserClient {
    @PostMapping
    User createUser(@RequestBody @Valid User user);

    @GetMapping
    List<User> getAllUsers();

    @GetMapping("/{userId}")
    User getUserById(@PathVariable Integer userId);

    @PutMapping("/{userId}")
    void updateUser(@PathVariable Integer userId, @RequestBody @Valid User user);

    @DeleteMapping("/{userId}")
    void deleteUser(@PathVariable Integer userId);

    @GetMapping("/username/{username}")
    User getUserByUsername(@PathVariable String username);

    @GetMapping("/email/{email}")
    User getUserByEmail(@PathVariable @Email String email);
}
