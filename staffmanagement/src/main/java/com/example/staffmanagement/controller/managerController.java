package com.example.staffmanagement.controller;
import com.example.staffmanagement.dto.manager.request.ManagerLoginRequest;
import com.example.staffmanagement.dto.manager.request.ManagerRegisterRequest;
import com.example.staffmanagement.dto.manager.request.ManagerUpdateRequest;
import com.example.staffmanagement.dto.manager.response.ManagerLoginResponse;
import com.example.staffmanagement.mapper.ManagerMapper;
import com.example.staffmanagement.service.ServiceImplemetation.managerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000" , allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/api/v1/manager")
public class managerController {

    @Autowired
    private managerServiceImpl managerServiceImpl;

    @Autowired
    private ManagerMapper managerMapper;
    @Qualifier("managerService")
    @Autowired
    private com.example.staffmanagement.service.managerService managerService;


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

//    @PutMapping("/update/{managerId}")
//    public ResponseEntity<Manager> updateManager(
//            @PathVariable Long managerId,
//            @RequestBody Manager updatedData) {
//        try {
//            Manager updatedManager = managerService.updateManager(managerId, updatedData);
//            return ResponseEntity.ok(updatedManager);
//        } catch (ManagerNotFoundException e) {
//            return ResponseEntity.notFound().build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

    @PostMapping("/update/{managerId}")
    public ResponseEntity<?> updateManagerDetails(@RequestBody ManagerUpdateRequest managerUpdateRequest) {
        return new ResponseEntity<>(managerServiceImpl.updateManager(managerUpdateRequest), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> findAllManagers() {
       return new ResponseEntity<>(managerMapper.managerListToDtoList(managerServiceImpl.findAllManagers()),HttpStatus.OK);
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
