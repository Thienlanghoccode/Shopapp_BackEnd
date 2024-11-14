package com.java.PTPMHDV13.Vinfast_Sales.mapper;

import com.java.PTPMHDV13.Vinfast_Sales.dto.request.UserRequestDTO;
import com.java.PTPMHDV13.Vinfast_Sales.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserRequestDTO userRequestDTO);

}
