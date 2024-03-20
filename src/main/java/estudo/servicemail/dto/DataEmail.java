package estudo.servicemail.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DataEmail(@NotBlank String name, @NotBlank @Email String to, @NotBlank String subject,
        @NotBlank String text, @NotBlank String category) {

}
