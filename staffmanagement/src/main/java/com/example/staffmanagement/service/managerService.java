package com.example.staffmanagement.service;

import com.example.staffmanagement.dto.manager.request.ManagerRegisterRequest;
import com.example.staffmanagement.dto.manager.request.ManagerUpdateRequest;
import com.example.staffmanagement.dto.manager.response.ApiResponse;
import com.example.staffmanagement.dto.manager.response.ManagerLoginResponse;
import com.example.staffmanagement.dto.manager.response.ManagerRegisterResponse;
import com.example.staffmanagement.model.Manager;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface managerService {
    List<Manager> findAllManagers();
    Manager findManagerById(int id);
    ApiResponse updateManager(ManagerUpdateRequest managerUpdateRequest);
    public ManagerRegisterResponse registerManager(ManagerRegisterRequest managerRegisterRequest);
    ManagerLoginResponse authenticateManager(String managerId, String password);
    public boolean deleteManagerById(String managerId);

}
