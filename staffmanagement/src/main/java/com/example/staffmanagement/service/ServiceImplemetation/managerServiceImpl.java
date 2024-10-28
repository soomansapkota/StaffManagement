package com.example.staffmanagement.service.ServiceImplemetation;
import com.example.staffmanagement.dto.request.ManagerRegisterRequest;
import com.example.staffmanagement.dto.request.ManagerUpdateRequest;
import com.example.staffmanagement.dto.response.ApiResponse;
import com.example.staffmanagement.dto.response.ManagerLoginResponse;
import com.example.staffmanagement.dto.response.ManagerRegisterResponse;
import com.example.staffmanagement.exceptionHandling.ManagerNotFoundException;
import com.example.staffmanagement.model.Manager;
import com.example.staffmanagement.repository.managerRepository;
import com.example.staffmanagement.service.managerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class managerServiceImpl implements managerService {

    private static final Logger logger = LoggerFactory.getLogger(managerServiceImpl.class);

    @Autowired
    private managerRepository managerRepo;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;


    @Override
    public ManagerRegisterResponse registerManager(ManagerRegisterRequest managerRegisterRequest) {
        ManagerRegisterResponse result;
        // Check if managerId already exists
        Optional<Manager> existingManager = Optional.ofNullable(managerRepo.findByManagerId(managerRegisterRequest.getManagerId()));

        if (existingManager.isPresent()) {
            // Log and return message indicating existence
            logger.warn("Registration failed: Manager ID {} already exists.", managerRegisterRequest.getManagerId());
            result = new ManagerRegisterResponse(
                    null,
                    null,
                    null,
                    "Manager already exists"
            );
        } else {// Create and set fields for new Manager entity
            Manager manager = new Manager();
            manager.setManagerId(managerRegisterRequest.getManagerId());
            manager.setName(managerRegisterRequest.getName());
            manager.setEmail(managerRegisterRequest.getEmail());
            manager.setPassword(passwordEncoder.encode(managerRegisterRequest.getPassword()));// Save the new manager entity
            Manager savedManager = managerRepo.save(manager);
            logger.info("Saved manager - ID: {}, Name: {}", savedManager.getManagerId(), savedManager.getName());// Return successful registration response
            result = new ManagerRegisterResponse(
                    savedManager.getManagerId(),
                    savedManager.getEmail(),
                    savedManager.getName(),
                    "Manager registered successfully"
            );
        }

        return result;
    }



    @Override
    public ManagerLoginResponse authenticateManager(String managerId, String password) {
        // Fetch the manager by managerId
        Manager manager = managerRepo.findByManagerId(managerId);

        // Check if manager is null and handle it
        if (manager == null) {
            // This line ensures the flow stops if the manager is not found
            logger.warn("Login failed: Manager not found for ID={}, Timestamp={}", managerId, LocalDateTime.now());
            throw new ManagerNotFoundException("Manager not found");
        }
        // Proceed with password check only if manager is not null
        if (passwordEncoder.matches(password, manager.getPassword())) {
            // Return a successful response
            logger.info("Login successful: ID={}, Name={}, Timestamp={}", manager.getManagerId(), manager.getName(), LocalDateTime.now());
            return new ManagerLoginResponse(
                    manager.getManagerId(),
                    manager.getName(),
                    "Login successfully"
            );
        } else {
            // Handle invalid credentials case
            logger.warn("Login failed: Invalid credentials for ID={}, Timestamp={}", managerId, LocalDateTime.now());
            throw new RuntimeException("Invalid credentials");
        }
    }

    @Override
    public List<Manager> findAllManagers() {
        return managerRepo.findAll();
    }

    @Override
    public Manager findManagerById(int id) {
        Optional<Manager> findById = managerRepo.findById(id);
        return findById.orElse(null);
    }

    @Override
    public ApiResponse updateManager(ManagerUpdateRequest managerUpdateRequest) {
        Manager existingManager = managerRepo.findByManagerId(managerUpdateRequest.getManagerId());
        Manager updatedManager;
        if (existingManager == null) {
            logger.warn("Manager not found for ID={}", managerUpdateRequest.getManagerId());
            throw new ManagerNotFoundException("Manager not found for ID=" + managerUpdateRequest.getManagerId());
        } else {
            // Update manager's fields
            existingManager.setManagerId(managerUpdateRequest.getManagerId());
            existingManager.setName(managerUpdateRequest.getName());
            existingManager.setEmail(managerUpdateRequest.getEmail());

            // Save the updated manager to the repository
            // Save the updated manager
            updatedManager = managerRepo.save(existingManager);

            // Log the update
            logger.info("Manager updated: ID={}, Name={}", updatedManager.getManagerId(), updatedManager.getName());
            // Return an API response with the message and the updated manager data
        }
        return new ApiResponse(true, "Manager updated successfully");
    }

    @Override
    public boolean deleteManagerById(String managerId) {
        Manager manager = managerRepo.findByManagerId(managerId);
        System.out.println(manager);
        if (manager != null) {
            managerRepo.delete(manager);
            logger.info("Manager deleted: ID={}", managerId);
            return true;
        } else {
            logger.warn("Manager not found for ID={}", managerId);
            return false;
        }

    }
}

