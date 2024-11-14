package com.java.PTPMHDV13.Vinfast_Sales.mapper;

import com.java.PTPMHDV13.Vinfast_Sales.dto.request.UserRequestDTO;
import com.java.PTPMHDV13.Vinfast_Sales.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "isAdmin", constant = "false")
    @Mapping(target = "status", constant = "ACTIVE")
    User toUserRegister(UserRequestDTO userRequestDTO);

}
