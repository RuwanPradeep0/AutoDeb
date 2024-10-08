package backend.backend.service;


import backend.backend.dto.user.UserLoginDTO;
import backend.backend.dto.user.UserProfileDTO;
import backend.backend.dto.user.UserProfileUpdateDTO;
import backend.backend.dto.user.UserRegistrationDTO;

public interface UserService {

    UserProfileDTO registerUser(UserRegistrationDTO registrationDTO);

    String authenticateUser(UserLoginDTO loginDTO);

    UserProfileDTO getUserProfile(String username);

    UserProfileDTO updateUserProfile(String username, UserProfileUpdateDTO updateDTO);
}

