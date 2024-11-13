package com.example.staffmanagement.mapper;
import com.example.staffmanagement.dto.ManagerDTO;
import com.example.staffmanagement.model.Manager;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ManagerMapper {
    ManagerDTO managerToDto(Manager manager);
    ManagerDTO managerDtoToManager(ManagerDTO managerDto);
    List<ManagerDTO> managerListToDtoList(List<Manager> managerList);

}
