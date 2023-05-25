package com.deskbooking.deskbooking.mapper;

import com.deskbooking.deskbooking.dto.UserDTO;
import com.deskbooking.deskbooking.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-04T16:47:39+0300",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        User user1 = null;

        user1 = user;

        UserDTO userDTO = new UserDTO( user1 );

        return userDTO;
    }
}
