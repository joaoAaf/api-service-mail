package estudo.servicemail.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import estudo.servicemail.dto.Email;
import estudo.servicemail.services.EmailService;

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
    public ResponseEntity<Void> postEmail(@RequestBody Email email) {
        try {
            emailService.sendEmail("noreply@devchinelo.com.br", email.to(), email.subject(), email.body());
            return ResponseEntity.noContent().build();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.unprocessableEntity().build();
        }
    }

}
