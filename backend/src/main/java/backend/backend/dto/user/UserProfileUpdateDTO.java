package backend.backend.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileUpdateDTO {

    @Size(min = 3, max = 50)
    private String username;

    @Email(message = "Email should be valid")
    private String email;

    private String firstName;

    private String lastName;
}

