package com.example.staffmanagement.controller;
import com.example.staffmanagement.dto.request.ManagerLoginRequest;
import com.example.staffmanagement.dto.request.ManagerRegisterRequest;
import com.example.staffmanagement.dto.request.ManagerUpdateRequest;
import com.example.staffmanagement.dto.response.ManagerLoginResponse;
import com.example.staffmanagement.model.Manager;
import com.example.staffmanagement.service.ServiceImplemetation.managerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/manager")
public class managerController {

    @Autowired
    private managerServiceImpl managerServiceImpl;

    // This endpoint is used to authenticate a manager by their ID and password.
    @PostMapping("/login")
    public ResponseEntity<?> getAllManager(@RequestBody ManagerLoginRequest loginRequest) {
        ManagerLoginResponse response = managerServiceImpl.authenticateManager(
                String.valueOf(loginRequest.getManagerId()),
                loginRequest.getPassword()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // This endpoint is used to register a new manager by accepting their details in the request body.
    @PostMapping("/register")
    public ResponseEntity<?> saveManagerDetails(@RequestBody ManagerRegisterRequest managerRegisterRequest) {
        return new ResponseEntity<>(managerServiceImpl.registerManager(managerRegisterRequest), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateManagerDetails(@RequestBody ManagerUpdateRequest managerUpdateRequest) {
        return new ResponseEntity<>(managerServiceImpl.updateManager(managerUpdateRequest), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> findAllManagers() {
       return new ResponseEntity<>(managerServiceImpl.findAllManagers(),HttpStatus.OK);
    }

    // This endpoint retrieves a manager's details based on the manager ID provided in the URL path.
    @GetMapping("/get/{managerId}")
    public ResponseEntity<?> getManagerFromId(@PathVariable Integer managerId) {
        return new ResponseEntity<>("User " + managerId, HttpStatus.OK);
    }

    // This endpoint deletes a manager based on their manager ID provided in the URL path.
    @DeleteMapping("/delete/{managerId}")
    public ResponseEntity<String> deleteManager(@PathVariable String managerId) {
        boolean isDeleted = managerServiceImpl.deleteManagerById(managerId);
        if (isDeleted) {
            return ResponseEntity.ok("Manager deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Manager not found");
        }
    }

}
