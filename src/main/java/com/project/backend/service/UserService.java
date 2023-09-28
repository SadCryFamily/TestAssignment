package com.project.backend.service;

import com.project.backend.pojo.User;

import java.util.Date;
import java.util.List;

public interface UserService {

     User createUser(User createUser);

     List<User> getAllUsers();

     boolean deleteUserByEmail(String email);

     List<User> getAllUserByBirthRange(Date from, Date to);

}
