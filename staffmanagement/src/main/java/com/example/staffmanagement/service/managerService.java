package com.example.staffmanagement.service;

import com.example.staffmanagement.dto.response.ManagerLoginResponse;
import com.example.staffmanagement.model.Manager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface managerService {
    List<Manager> findAllManagers();
    Manager findManagerById(int id);
    Manager updateManager(Manager manager);
    Manager registerManager(Manager manager);
    ManagerLoginResponse authenticateManager(String managerId, String password);
    void deleteManagerById(int id);

}
