package org.raman.intern.studentmgmt.jwt;
import jakarta.validation.Valid;
import org.raman.intern.studentmgmt.entity.Student;
import org.raman.intern.studentmgmt.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class JwtAuthenticationController {

    private final JwtTokenService tokenService;

    private final AuthenticationManager authenticationManager;
    private final StudentService studentService;

    public JwtAuthenticationController(JwtTokenService tokenService,
                                       AuthenticationManager authenticationManager, StudentService studentService) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.studentService = studentService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtTokenResponse> generateToken(@RequestBody JwtTokenRequest jwtTokenRequest) {

        var authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        jwtTokenRequest.username(),
                        jwtTokenRequest.password());

        var authentication =
                authenticationManager.authenticate(authenticationToken);

        var token = tokenService.generateToken(authentication);

        return ResponseEntity.ok(new JwtTokenResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Student> createStudent(@RequestBody @Valid Student student) {
        Student createdStudent = studentService.addStudent(student);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .replacePath("/students/{id}")
                .buildAndExpand(createdStudent.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdStudent);
    }
}