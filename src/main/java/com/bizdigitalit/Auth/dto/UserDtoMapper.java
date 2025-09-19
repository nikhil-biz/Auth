package com.bizdigitalit.Auth.dto;

import com.bizdigitalit.Auth.model.Role;
import com.bizdigitalit.Auth.model.User;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserDtoMapper {

    public static User toUser(SignupRequest dto){
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        Set<Role> roles = dto.getRoles().stream().map(r -> {
            Role role = new Role();
            role.setName(r.getName());
            return role;
        }).collect(Collectors.toSet());
        user.setRoles(roles);
        user.getRoles().size();
        return user;
    }

    public static SignupRequest toDto(User user){
        SignupRequest userDto = new SignupRequest();
        userDto.setEmail(user.getEmail());
        if (user.getRoles() != null) {
            Set<RoleDto> roles = user.getRoles().stream()
                    .map(role -> new RoleDto(role.getName()))
                    .collect(Collectors.toSet());
            userDto.setRoles(roles);
        }
        return userDto;
    }
}
