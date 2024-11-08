package com.java.PTPMHDV13.Vinfast_Sales.controller;

import com.java.PTPMHDV13.Vinfast_Sales.dto.request.UserRequestDTO;
import com.java.PTPMHDV13.Vinfast_Sales.dto.response.ResponseData;
import com.java.PTPMHDV13.Vinfast_Sales.entity.User;
import com.java.PTPMHDV13.Vinfast_Sales.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User Controller")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Get all user", description = "API get all user")
    @GetMapping
    public ResponseData<List<User>> getAllUsers() {
        return new ResponseData<>(HttpStatus.OK.value(),"Request get all of users",
                userService.findAll());
    }

    @Operation(summary = "Get user information", description = "API get user by id")
    @GetMapping("/{id}")
    public ResponseData<User> getUserById(@PathVariable Long id) {
        return new ResponseData<>(HttpStatus.OK.value(),
                "Request get user detail, userID = " + id,
                userService.findById(id));
    }

    @Operation(summary = "Create new user", description = "API create new user")
    @PostMapping
    public ResponseData<?> addUser(@Valid @RequestBody UserRequestDTO user) {
        userService.saveUser(user);
        return new ResponseData<>(HttpStatus.CREATED.value(),
                "User added successfully" , 1);
    }

    @Operation(summary = "Change user status", description = "API change user's status")
    @PutMapping("/{userId}")
    public ResponseData<?> updateUser(@PathVariable Long userId, @RequestBody UserRequestDTO user) {
        return new ResponseData<>(HttpStatus.ACCEPTED.value(),
                "Request update user, userId = " + userId);
    }

    @Operation(summary = "Delete user", description = "API delete user by id")
    @DeleteMapping("/{userId}")
    public ResponseData<?> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "User was deleted successfully");
    }
}
