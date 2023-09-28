package com.project.backend.pojo;

import com.project.backend.annotation.Phone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @NotNull(message = "Firstname can't be null")
    @Size(min = 6, message = "Firstname must be longer than 6")
    private String userFirstName;

    @NotNull(message = "Firstname can't be null")
    @Size(min = 6, message = "Lastname must be longer than 6")
    private String userLastName;

    @Email(message = "Wrong format of email")
    @Size(min = 6, message = "Email length must be longer than 6")
    @NotNull(message = "Email can't be null")
    private String userEmail;

    @NotNull(message = "Birth date can't be null")
    private Date userBirthDate;

    @NotNull(message = "Address can't be null")
    @Size(min = 6, message = "Address must be longer than 6")
    private String userAddress;

    @Phone(message = "Wrong format of phone number")
    @NotNull(message = "Phone can't be null")
    private String userPhoneNumber;

}
