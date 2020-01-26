package io.github.h2kb.dto;

import io.github.h2kb.entity.User;
import org.springframework.stereotype.Service;

@Service
public class DtoConverter {

    public UserDto userToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setLogin(user.getLogin());

        return userDto;
    }
}
