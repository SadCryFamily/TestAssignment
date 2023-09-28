package com.project.backend.service;

import com.project.backend.exception.UserRegisterNotAllowedByBirthException;
import com.project.backend.pojo.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final List<User> usersList;

    @Value("${user.age.minimal-allowed}")
    private long userMinimalAllowedAge;

    @Override
    public User createUser(User createUser) {

        if (isUserCanRegisterByBirth(createUser.getUserBirthDate())) {
            log.error("ERROR CREATE USER -> firstName: {}, email: {}", createUser.getUserFirstName(), createUser.getUserEmail());
            throw new UserRegisterNotAllowedByBirthException("Cannot create user younger than 18");
        }

        usersList.add(createUser);

        log.info("CREATE USER -> firstName: {}, email: {}", createUser.getUserFirstName(), createUser.getUserEmail());
        return createUser;
    }

    @Override
    public List<User> getAllUsers() {
        log.info("GET ALL USERS -> current size: {}", usersList.size());
        return usersList;
    }

    @Override
    public boolean deleteUserByEmail(String email) {
        log.info("DELETE USER -> email: {}", email);
        return usersList.removeIf(user -> user.getUserEmail().equals(email));
    }

    @Override
    public List<User> getAllUserByBirthRange(Date from, Date to) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        log.info("GET ALL USERS(from : {}, to: {}) -> current size: {}",
                dateFormat.format(from), dateFormat.format(to), usersList.size());

        return usersList.stream()
                .filter(user -> user.getUserBirthDate().after(from) && user.getUserBirthDate().before(to))
                .collect(Collectors.toList());
    }

    private boolean isUserCanRegisterByBirth(Date birthDate) {

        LocalDate currentDate = LocalDate.now();
        LocalDate eighteenYearsAgoDate = currentDate.minusYears(userMinimalAllowedAge);

        LocalDate convertedBirthDate = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return convertedBirthDate.isAfter(eighteenYearsAgoDate);

    }
}
