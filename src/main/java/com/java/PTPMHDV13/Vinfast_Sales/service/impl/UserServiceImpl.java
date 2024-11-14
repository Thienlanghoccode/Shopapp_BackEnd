package com.java.PTPMHDV13.Vinfast_Sales.service.impl;

import com.java.PTPMHDV13.Vinfast_Sales.dto.request.UserRequestDTO;
import com.java.PTPMHDV13.Vinfast_Sales.entity.User;
import com.java.PTPMHDV13.Vinfast_Sales.enums.UserStatus;
import com.java.PTPMHDV13.Vinfast_Sales.exception.AlReadyExistException;
import com.java.PTPMHDV13.Vinfast_Sales.mapper.UserMapper;
import com.java.PTPMHDV13.Vinfast_Sales.repository.UserRepository;
import com.java.PTPMHDV13.Vinfast_Sales.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetailsService getUserDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User Not Found"));
    }

    @Override
    @Transactional
    public void saveUser(UserRequestDTO dto) {
        User user = userMapper.toUser(dto);
        if(checkForDuplicate(user))
            throw new AlReadyExistException("User Already Exists");
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        if (dto.getStatus() == null) {
            user.setStatus(UserStatus.ACTIVE);
        } else {
            user.setStatus(dto.getStatus());
        }
        user.setIsAdmin(dto.getIsAdmin() != null);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(UserRequestDTO request, Long id) {
        User user = findById(id);
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setStatus(request.getStatus());
        user.setIsAdmin(request.getIsAdmin());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
        } else throw new NoSuchElementException("User Not Found");
    }

    public boolean checkForDuplicate(User user) {
        return userRepository.findByEmail(user.getEmail()) != null;
    }
}
