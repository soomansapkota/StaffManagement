package com.example.staffmanagement.controller;
import com.example.staffmanagement.repository.staffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000" , allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/api/v1/staff")
public class staffController {

    @Autowired
    private staffRepository staffRepo;

}
