package com.example.staffmanagement.service;

import com.example.staffmanagement.model.Staff;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface staffService {
  Staff getStaffById(int id);
  List<Staff> findAllStaff();
  public void addStaff(Staff staff);
  public void updateStaff(Staff staff);
  public void deleteStaff(int id);

}
