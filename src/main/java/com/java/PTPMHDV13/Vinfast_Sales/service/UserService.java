package com.java.PTPMHDV13.Vinfast_Sales.service;

import com.java.PTPMHDV13.Vinfast_Sales.dto.request.UserRequestDTO;
import com.java.PTPMHDV13.Vinfast_Sales.dto.response.product.TopSellingProduct;
import com.java.PTPMHDV13.Vinfast_Sales.dto.response.user.*;
import com.java.PTPMHDV13.Vinfast_Sales.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    UserDetailsService getUserDetailsService();

    List<User> findAll();

    User findById(Long id);

    void saveUser(UserRequestDTO userRequestDTO);

    void updateUser(UserRequestDTO request, Long id);

    void deleteUser(Long id);

    List<TotalSpentByUserDTO> getCustomerRevenue();

    List<ActiveUsersByMonthDTO> getUserActivityByTime();

    List<CustomerInteractionDTO> getProductInteractions();

    List<TopSpendingUsersDTO> getTop3SpendingCustomers();

    List<OrderBehaviorAnalysisDTO> getOrderBehaviorAnalysis();

    List<UserOver180DaysDTO> getInactiveCustomersOver180days();

    List<CustomerRFMAnalysisDTO> getRFMAnalysis();

}
