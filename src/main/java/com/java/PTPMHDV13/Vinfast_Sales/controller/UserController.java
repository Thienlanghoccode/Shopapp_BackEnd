package com.java.PTPMHDV13.Vinfast_Sales.controller;

import com.java.PTPMHDV13.Vinfast_Sales.dto.request.UserRequestDTO;
import com.java.PTPMHDV13.Vinfast_Sales.dto.response.ResponseData;
import com.java.PTPMHDV13.Vinfast_Sales.dto.response.user.*;
import com.java.PTPMHDV13.Vinfast_Sales.entity.User;
import com.java.PTPMHDV13.Vinfast_Sales.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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

    @Operation(summary = "Update user", description = "API update user by id")
    @PutMapping("/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseData<?> updateUser(@PathVariable Long userId,@Valid @RequestBody UserRequestDTO request) {
        userService.updateUser(request, userId);
        return new ResponseData<>(HttpStatus.ACCEPTED.value(),
                "Request update user, userId = " + userId);
    }

    @Operation(summary = "Delete user", description = "API delete user by id")
    @DeleteMapping("/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseData<?> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "User was deleted successfully");
    }

    @Operation(summary = "Get customer revenue", description = "API to get revenue of each customer")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/user-analyst/customer-revenue")
    public ResponseData<List<TotalSpentByUserDTO>> getCustomerRevenue() {
        return new ResponseData<>(HttpStatus.OK.value(), "Fetched customer revenue",
                userService.getCustomerRevenue());
    }

    @Operation(summary = "Get user activity by time", description = "API to get user activity by month and year")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/user-analyst/user-activity")
    public ResponseData<List<ActiveUsersByMonthDTO>> getUserActivityByTime() {
        return new ResponseData<>(HttpStatus.OK.value(), "Fetched user activity by time",
                userService.getUserActivityByTime());
    }

    @Operation(summary = "Get product interactions", description = "API to get product interactions by users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/user-analyst/product-interactions")
    public ResponseData<List<CustomerInteractionDTO>> getProductInteractions() {
        return new ResponseData<>(HttpStatus.OK.value(), "Fetched product interactions",
                userService.getProductInteractions());
    }

    @Operation(summary = "Get top 3 spending customers", description = "API to get top 3 customers who spent the most")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/user-analyst/top-spenders")
    public ResponseData<List<TopSpendingUsersDTO>> getTop3SpendingCustomers() {
        return new ResponseData<>(HttpStatus.OK.value(), "Fetched top 3 spending customers",
                userService.getTop3SpendingCustomers());
    }

    @Operation(summary = "Analyze order behavior", description = "API to analyze customer order behavior")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/user-analyst/order-behavior")
    public ResponseData<List<OrderBehaviorAnalysisDTO>> getOrderBehaviorAnalysis() {
        return new ResponseData<>(HttpStatus.OK.value(), "Fetched order behavior analysis",
                userService.getOrderBehaviorAnalysis());
    }

    @Operation(summary = "Get less active customers", description = "API to get customers who haven't purchased in over 180 days")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/user-analyst/less-active-customers")
    public ResponseData<List<UserOver180DaysDTO>> getInactiveCustomersOver180days() {
        return new ResponseData<>(HttpStatus.OK.value(), "Fetched inactive customers over 180 days",
                userService.getInactiveCustomersOver180days());
    }

    @Operation(summary = "Get RFM analysis", description = "API to get customer RFM analysis")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/user-analyst/rfm-analysis")
    public ResponseData<List<CustomerRFMAnalysisDTO>> getRFMAnalysis() {
        return new ResponseData<>(HttpStatus.OK.value(), "Fetched RFM analysis",
                userService.getRFMAnalysis());
    }
}
