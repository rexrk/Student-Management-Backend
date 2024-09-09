package org.raman.intern.studentmgmt.entity;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Student {
    @Id
    private String id;

    @NotEmpty(message = "Name is required")
    @Size(min = 5, max = 50, message = "mame should be between 5 and 50 characters")
    private String name;

    @NotNull(message = "Contact is required")
    @Digits(integer = 10, fraction = 0, message = "contact must be 10 digits number")
    private Long contactDetails;

    @NotEmpty(message = "Address is required")
    private String address;

    @NotNull(message = "Pin code is required")
    @Digits(integer = 6, fraction = 0, message = "pin code must be 6 digits")
    private Integer pinCode;

    @NotEmpty(message = "username is required")
    private String username;

    @NotEmpty(message = "password is required")
    private String password;

    @NotNull(message = "role is null")
    private Roles role;
}