package com.bizdigitalit.Auth.dto;

import com.bizdigitalit.Auth.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {

    public static User toUser(UserDto dto){
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    public static UserDto toDto(User user){
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
