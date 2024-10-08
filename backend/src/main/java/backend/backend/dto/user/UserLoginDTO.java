package backend.backend.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginDTO {

    @NotBlank(message = "Username or Email is mandatory")
    private String usernameOrEmail;

    @NotBlank(message = "Password is mandatory")
    private String password;
}
