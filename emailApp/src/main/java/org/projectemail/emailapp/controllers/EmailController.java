package org.projectemail.emailapp.controllers;

import org.projectemail.emailapp.dtos.RequestDto;
import org.projectemail.emailapp.dtos.ResponseDto;
import org.projectemail.emailapp.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    private final EmailService emailService;
    private final String authorizationToken1;
    private final String authorizationToken2;

    @Autowired
    public EmailController(EmailService emailService,
                           @Value("${authorization.token1}") String authorizationToken1,
                           @Value("${authorization.token2}") String authorizationToken2) {
        this.emailService = emailService;
        this.authorizationToken1 = authorizationToken1;
        this.authorizationToken2 = authorizationToken2;
    }

    @PostMapping("/send-email")
    public ResponseEntity<ResponseDto> sendEmail(@RequestHeader("Authorization") String authToken, @RequestBody RequestDto requestDto) {

        String expectedToken = authorizationToken1 + authorizationToken2;
        if (!authToken.equals(expectedToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDto("Unauthorized access"));
        }

        boolean emailSent = emailService.sendEmail(requestDto);
        if (emailSent) {
            return ResponseEntity.ok(new ResponseDto("Email sent successfully!"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("Failed to send email!"));
        }
    }
}