package com.example.staffmanagement.service.ServiceImplemetation;

import com.example.staffmanagement.model.Staff;
import com.example.staffmanagement.repository.staffRepository;
import com.example.staffmanagement.service.staffService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class staffServiceImpl implements staffService {

    @Autowired
    private staffRepository staffRepo;

    @Override
    public Staff getStaffById(int id) {
        return null;
    }

    @Override
    public List<Staff> findAllStaff() {
        return staffRepo.findAll();
    }

    @Override
    public void addStaff(Staff staff) {
    }

    @Override
    public void updateStaff(Staff staff) {

    }

    @Override
    public void deleteStaff(int id) {

    }
}
