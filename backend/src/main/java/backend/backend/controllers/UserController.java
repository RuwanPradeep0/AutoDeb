package backend.backend.controllers;



import backend.backend.dto.user.UserLoginDTO;
import backend.backend.dto.user.UserProfileDTO;
import backend.backend.dto.user.UserProfileUpdateDTO;
import backend.backend.dto.user.UserRegistrationDTO;
import backend.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // POST /api/v1/users/register
    @PostMapping("/register")
    public ResponseEntity<UserProfileDTO> registerUser(@Valid @RequestBody UserRegistrationDTO registrationDTO) {
        UserProfileDTO userProfile = userService.registerUser(registrationDTO);
        return new ResponseEntity<>(userProfile, HttpStatus.CREATED);
    }

    // POST /api/v1/users/login
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody UserLoginDTO loginDTO) {
        String token = userService.authenticateUser(loginDTO);
        return ResponseEntity.ok(token);
    }

    // GET /api/v1/users/profile
    @GetMapping("/profile")
    public ResponseEntity<UserProfileDTO> getUserProfile(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        UserProfileDTO userProfile = userService.getUserProfile(username);
        return ResponseEntity.ok(userProfile);
    }

    // PUT /api/v1/users/profile
    @PutMapping("/profile")
    public ResponseEntity<UserProfileDTO> updateUserProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody UserProfileUpdateDTO updateDTO) {
        String username = userDetails.getUsername();
        UserProfileDTO updatedProfile = userService.updateUserProfile(username, updateDTO);
        return ResponseEntity.ok(updatedProfile);
    }
}
