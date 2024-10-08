package backend.backend.service.impl;



import backend.backend.dto.user.UserLoginDTO;
import backend.backend.dto.user.UserProfileDTO;
import backend.backend.dto.user.UserProfileUpdateDTO;
import backend.backend.dto.user.UserRegistrationDTO;
import backend.backend.exception.ResourceAlreadyExistsException;
import backend.backend.exception.ResourceNotFoundException;
import backend.backend.models.User;
import backend.backend.repository.UserRepository;
import backend.backend.service.UserService;
import backend.backend.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserProfileDTO registerUser(UserRegistrationDTO registrationDTO) {
        // Check if username or email already exists
        if (userRepository.existsByUsername(registrationDTO.getUsername())) {
            throw new ResourceAlreadyExistsException("Username is already taken!");
        }

        if (userRepository.existsByEmail(registrationDTO.getEmail())) {
            throw new ResourceAlreadyExistsException("Email is already in use!");
        }

        // Create new user
        User user = User.builder()
                .username(registrationDTO.getUsername())
                .email(registrationDTO.getEmail())
                .password(passwordEncoder.encode(registrationDTO.getPassword()))
                .firstName(registrationDTO.getFirstName())
                .lastName(registrationDTO.getLastName())
                .build();

        User savedUser = userRepository.save(user);

        return mapToUserProfileDTO(savedUser);
    }

    @Override
    public String authenticateUser(UserLoginDTO loginDTO) {
        try {
            // Authenticate the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getUsernameOrEmail(),
                            loginDTO.getPassword()
                    )
            );
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Invalid username/email or password");
        }

        // Generate JWT token
        User user = userRepository.findByUsername(loginDTO.getUsernameOrEmail())
                .orElse(userRepository.findByEmail(loginDTO.getUsernameOrEmail())
                        .orElseThrow(() -> new ResourceNotFoundException("User not found")));

        return jwtTokenUtil.generateToken(user);
    }

    @Override
    public UserProfileDTO getUserProfile(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));

        return mapToUserProfileDTO(user);
    }

    @Override
    public UserProfileDTO updateUserProfile(String username, UserProfileUpdateDTO updateDTO) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));

        if (updateDTO.getUsername() != null && !updateDTO.getUsername().equals(user.getUsername())) {
            if (userRepository.existsByUsername(updateDTO.getUsername())) {
                throw new ResourceAlreadyExistsException("Username is already taken!");
            }
            user.setUsername(updateDTO.getUsername());
        }

        if (updateDTO.getEmail() != null && !updateDTO.getEmail().equals(user.getEmail())) {
            if (userRepository.existsByEmail(updateDTO.getEmail())) {
                throw new ResourceAlreadyExistsException("Email is already in use!");
            }
            user.setEmail(updateDTO.getEmail());
        }

        if (updateDTO.getFirstName() != null) {
            user.setFirstName(updateDTO.getFirstName());
        }

        if (updateDTO.getLastName() != null) {
            user.setLastName(updateDTO.getLastName());
        }

        User updatedUser = userRepository.save(user);
        return mapToUserProfileDTO(updatedUser);
    }

    // Utility method to map User to UserProfileDTO
    private UserProfileDTO mapToUserProfileDTO(User user) {
        return UserProfileDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .createdAt(user.getCreatedAt().toString())
                .updatedAt(user.getUpdatedAt().toString())
                .build();
    }
}
