package com.bizdigitalit.Auth.service;

import com.bizdigitalit.Auth.model.User;
import com.bizdigitalit.Auth.model.UserPrinciple;
import com.bizdigitalit.Auth.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        User user = userRepository.findByEmail(email);
        if(!userRepository.existsByEmail(email)){
            throw new UsernameNotFoundException("User not found with the email!");
        }
        return new UserPrinciple(user);
    }
}
