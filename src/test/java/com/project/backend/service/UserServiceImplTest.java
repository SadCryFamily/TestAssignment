package com.project.backend.service;

import com.project.backend.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    void createUser() {

        LocalDate birthLocalDate = LocalDate.of(2002, 10, 10);
        Instant birthInstant = birthLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();

        User testableUser = new User(
                "Testable",
                "Testable",
                "testable@gmail.com",
                Date.from(birthInstant),
                "Testable Address",
                "+380463215632"
        );

        User actualUser = userService.createUser(testableUser);

        assertEquals(testableUser, actualUser);
    }

    @Test
    void getAllUsers() {

        List<User> expectedList = Collections.emptyList();
        List<User> actualList = userService.getAllUsers();

        assertEquals(expectedList.size(), actualList.size());

    }

    @Test
    void deleteUserByEmail() {

        String testEmail = "test@gmail.com";

        boolean expectedResponse = false;
        boolean actualResponse = userService.deleteUserByEmail(testEmail);

        assertEquals(expectedResponse, actualResponse);

    }

    @Test
    void getAllUserByBirthRange() {

        LocalDate from = LocalDate.of(2002, 10, 10);
        LocalDate to = LocalDate.of(2003, 10, 10);

        Instant fromInstant = from.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Instant toInstant = to.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();

        Date fromDate = Date.from(fromInstant);
        Date toDate = Date.from(toInstant);

        List<User> expectedList = Collections.emptyList();
        List<User> actualList = userService.getAllUserByBirthRange(fromDate, toDate);

        assertEquals(expectedList.size(), actualList.size());

    }
}