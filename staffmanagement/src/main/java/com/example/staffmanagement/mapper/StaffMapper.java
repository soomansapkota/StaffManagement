package com.example.staffmanagement.mapper;


import com.example.staffmanagement.dto.staffDTO;
import com.example.staffmanagement.model.Staff;

import java.util.List;

public interface StaffMapper {
    staffDTO staffToDto(Staff staff);
    staffDTO staffDtoToManager(staffDTO staffDto);
    List<staffDTO> staffListToDtoList(List<Staff> staffList);

}
