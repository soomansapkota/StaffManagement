package com.example.staffmanagement.service.ServiceImplemetation;

import com.example.staffmanagement.dto.response.ManagerLoginResponse;
import com.example.staffmanagement.exceptionHandling.UserNotFoundException;
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
    public Manager registerManager(Manager manager) {
        String encodedPassword = passwordEncoder.encode(manager.getPassword());
        manager.setPassword(encodedPassword);
        return managerRepo.save(manager);
    }


    @Override
    public ManagerLoginResponse authenticateManager(String managerId, String password) {
        // Fetch the manager by managerId
        Manager manager = managerRepo.findByManagerId(managerId);

        // Check if manager is null and handle it
        if (manager == null) {
            // This line ensures the flow stops if the manager is not found
            logger.warn("Login failed: User not found for ID={}, Timestamp={}", managerId, LocalDateTime.now());
            throw new UserNotFoundException("User not found");
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
    public Manager updateManager(Manager manager) {
        return managerRepo.save(manager);
    }



    @Override
    public void deleteManagerById(int id) {
        managerRepo.delete(findManagerById(id));
    }
}
