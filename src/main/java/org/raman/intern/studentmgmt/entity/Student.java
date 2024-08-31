package org.raman.intern.studentmgmt.entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "Contact is required")
    @Min(value = 1000000000L, message = "Contact must be a valid 10-digit number")
    private Long contactDetails;

    @NotEmpty(message = "Address is required")
    private String address;

    @NotNull(message = "Pin code is required")
    @Min(value = 100000, message = "Pin code must be a 6-digit number")
    private Integer pinCode;
}