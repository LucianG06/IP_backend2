package com.deskbooking.deskbooking.mapper;
import com.deskbooking.deskbooking.model.User;
import org.mapstruct.Mapper;
import com.deskbooking.deskbooking.dto.UserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDTO(User user);
}
