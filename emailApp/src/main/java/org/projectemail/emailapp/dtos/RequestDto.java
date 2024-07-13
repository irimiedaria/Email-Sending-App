package org.projectemail.emailapp.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class RequestDto implements Serializable {

    @NotNull
    private UUID id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    @Email(message = "Invalid email format!")
    private String emailReceiver;

    @NotNull
    private String emailSubject;

    @NotNull
    private String emailBody;
}
