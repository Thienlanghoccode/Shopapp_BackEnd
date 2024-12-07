package com.java.PTPMHDV13.Vinfast_Sales.service.impl;

import com.java.PTPMHDV13.Vinfast_Sales.dto.request.UserRequestDTO;
import com.java.PTPMHDV13.Vinfast_Sales.dto.response.user.*;
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

import java.math.BigDecimal;
import java.util.ArrayList;
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
        if (checkForDuplicate(user))
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
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else throw new NoSuchElementException("User Not Found");
    }

    @Override
    public List<TotalSpentByUserDTO> getCustomerRevenue() {
        List<Object[]> results = userRepository.getTotalSpentByUser();
        List<TotalSpentByUserDTO> totalSpentByUserList = new ArrayList<>();
        for (Object[] row : results) {
            String username = (String) row[0];
            BigDecimal totalSpent = (BigDecimal) row[1];
            totalSpentByUserList.add(TotalSpentByUserDTO.builder()
                    .username(username)
                    .totalSpent(totalSpent)
                    .build());
        }
        return totalSpentByUserList;
    }

    @Override
    public List<ActiveUsersByMonthDTO> getUserActivityByTime() {
        List<Object[]> results = userRepository.getActiveUsersByMonth();
        List<ActiveUsersByMonthDTO> activeUsersByMonthList = new ArrayList<>();
        for (Object[] row : results) {
            Integer year = (Integer) row[0];
            Integer month = (Integer) row[1];
            Integer activeUsers = (Integer) row[2];
            activeUsersByMonthList.add(ActiveUsersByMonthDTO.builder()
                    .year(year)
                    .month(month)
                    .activeUsers(activeUsers)
                    .build());
        }
        return activeUsersByMonthList;
    }

    @Override
    public List<CustomerInteractionDTO> getProductInteractions() {
        List<Object[]> results = userRepository.getCustomerInteraction();
        List<CustomerInteractionDTO> customerInteractionList = new ArrayList<>();
        for (Object[] row : results) {
            String productName = (String) row[0];
            Integer uniqueVisitors = (Integer) row[1];
            int quantitySold = row[2] != null ? (Integer) row[2] : 0;
            BigDecimal totalRevenue = row[3] != null ? (BigDecimal) row[3] : BigDecimal.ZERO;
            customerInteractionList.add(CustomerInteractionDTO.builder()
                    .productName(productName)
                    .uniqueVisitors(uniqueVisitors)
                    .quantitySold(quantitySold)
                    .totalRevenue(totalRevenue)
                    .build());
        }
        return customerInteractionList;
    }

    @Override
    public List<TopSpendingUsersDTO> getTop3SpendingCustomers() {
        List<Object[]> results = userRepository.getTop3SpendingUsers();
        List<TopSpendingUsersDTO> topSpendingUsersList = new ArrayList<>();
        for (Object[] row : results) {
            String username = (String) row[0];
            Integer productsBought = (Integer) row[1];
            BigDecimal totalSpent = (BigDecimal) row[2];
            topSpendingUsersList.add(TopSpendingUsersDTO.builder()
                    .username(username)
                    .productsBought(productsBought)
                    .totalSpent(totalSpent)
                    .build());
        }
        return topSpendingUsersList;
    }

    @Override
    public List<OrderBehaviorAnalysisDTO> getOrderBehaviorAnalysis() {
        List<Object[]> results = userRepository.getOrderBehaviorAnalysis();
        List<OrderBehaviorAnalysisDTO> orderBehaviorAnalysisList = new ArrayList<>();
        for (Object[] row : results) {
            String username = (String) row[0];
            Integer totalOrders = (Integer) row[1];
            Integer averageItemsPerOrder = (Integer) row[2];
            orderBehaviorAnalysisList.add(OrderBehaviorAnalysisDTO.builder()
                    .username(username)
                    .totalOrders(totalOrders)
                    .averageItemsPerOrder(averageItemsPerOrder)
                    .build());
        }
        return orderBehaviorAnalysisList;
    }

    @Override
    public List<UserOver180DaysDTO> getInactiveCustomersOver180days() {
        List<Object[]> results = userRepository.getUserOver180days();
        List<UserOver180DaysDTO> inactiveCustomersList = new ArrayList<>();
        for (Object[] row : results) {
            String username = (String) row[0];
            Integer purchaseFrequency = (Integer) row[1];
            Integer lastPurchaseDays = (Integer) row[2];
            inactiveCustomersList.add(UserOver180DaysDTO.builder()
                    .username(username)
                    .purchaseFrequency(purchaseFrequency)
                    .lastPurchaseDays(lastPurchaseDays)
                    .build());
        }
        return inactiveCustomersList;
    }

    @Override
    public List<CustomerRFMAnalysisDTO> getRFMAnalysis() {
        List<Object[]> results = userRepository.getCustomerRFMAnalysis();
        List<CustomerRFMAnalysisDTO> rfmAnalysisList = new ArrayList<>();
        for (Object[] row : results) {
            String username = (String) row[0];
            String recencySegment = (String) row[1];
            String frequencySegment = (String) row[2];
            String monetarySegment = (String) row[3];
            rfmAnalysisList.add(CustomerRFMAnalysisDTO.builder()
                    .username(username)
                    .recencySegment(recencySegment)
                    .frequencySegment(frequencySegment)
                    .monetarySegment(monetarySegment)
                    .build());
        }
        return rfmAnalysisList;
    }


    public boolean checkForDuplicate(User user) {
        return userRepository.findByEmail(user.getEmail()) != null;
    }
}
