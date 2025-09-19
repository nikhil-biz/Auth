package com.bizdigitalit.Auth.service;

import com.bizdigitalit.Auth.dto.UserDtoMapper;
import com.bizdigitalit.Auth.dto.UserDto;
import com.bizdigitalit.Auth.exception.UserNotFoundException;
import com.bizdigitalit.Auth.model.User;
import com.bizdigitalit.Auth.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, UserDtoMapper userDtoMapper, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.userDtoMapper = userDtoMapper;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String addUser(UserDto userDto) {
        if(userRepository.existsByEmail(userDto.getEmail())){
            return " User is already present in db!";
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(userDtoMapper.toUser(userDto));
        return "User registered successfully!";
    }


    public String signInUser(UserDto userSignInDto) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userSignInDto.getEmail(),userSignInDto.getPassword()));
        return authentication.isAuthenticated() ? jwtService.generateToken(userSignInDto) : " authentication failed!";
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> userDtoMapper.toDto(user))
                .collect(Collectors.toList());
    }

    public UserDto getUserByEmail(String email) throws UserNotFoundException {
        if(!userRepository.existsByEmail(email)){
            throw new UserNotFoundException("User not found with email "+email);
        }
        return UserDtoMapper.toDto(userRepository.findByEmail(email));
    }
}
