package com.organicautonomy.userservice.dao;

import com.organicautonomy.userservice.dto.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    User findUserByUsername(String username);
    User findUserByEmail(String email);
}
