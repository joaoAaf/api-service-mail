package estudo.servicemail.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import estudo.servicemail.dto.DataEmail;
import estudo.servicemail.services.EmailService;
import jakarta.validation.Valid;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("email")
public class EmailController {
    
    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<Void> postEmail(@RequestBody @Valid DataEmail email) {
        try {
            emailService.sendEmail("noreply@devchinelo.com.br", email);
            return ResponseEntity.noContent().build();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.unprocessableEntity().build();
        }
    }

}
