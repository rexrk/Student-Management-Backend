package org.raman.intern.studentmgmt.exception;

import lombok.*;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ErrorDetails {
    private HttpStatusCode status;
    private String message;
    private LocalDateTime timestamp;
}