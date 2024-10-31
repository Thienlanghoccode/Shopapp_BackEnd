package com.java.PTPMHDV13.Vinfast_Sales.service;

import com.java.PTPMHDV13.Vinfast_Sales.dto.request.UserRequestDTO;
import com.java.PTPMHDV13.Vinfast_Sales.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    UserDetailsService getUserDetailsService();

    List<User> findAll();

    User findById(Long id);

    void saveUser(UserRequestDTO userRequestDTO);

    Boolean updateUser(User user);

    void deleteUser(Long id);

}
