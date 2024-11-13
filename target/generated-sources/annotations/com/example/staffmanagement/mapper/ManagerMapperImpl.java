package com.example.staffmanagement.mapper;

import com.example.staffmanagement.dto.ManagerDTO;
import com.example.staffmanagement.model.Manager;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-31T20:26:21+0545",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class ManagerMapperImpl implements ManagerMapper {

    @Override
    public ManagerDTO managerToDto(Manager manager) {
        if ( manager == null ) {
            return null;
        }

        ManagerDTO managerDTO = new ManagerDTO();

        managerDTO.setId( manager.getId() );
        managerDTO.setManagerId( manager.getManagerId() );
        managerDTO.setName( manager.getName() );
        managerDTO.setEmail( manager.getEmail() );

        return managerDTO;
    }

    @Override
    public ManagerDTO managerDtoToManager(ManagerDTO managerDto) {
        if ( managerDto == null ) {
            return null;
        }

        ManagerDTO managerDTO = new ManagerDTO();

        managerDTO.setId( managerDto.getId() );
        managerDTO.setManagerId( managerDto.getManagerId() );
        managerDTO.setName( managerDto.getName() );
        managerDTO.setEmail( managerDto.getEmail() );

        return managerDTO;
    }

    @Override
    public List<ManagerDTO> managerListToDtoList(List<Manager> managerList) {
        if ( managerList == null ) {
            return null;
        }

        List<ManagerDTO> list = new ArrayList<ManagerDTO>( managerList.size() );
        for ( Manager manager : managerList ) {
            list.add( managerToDto( manager ) );
        }

        return list;
    }
}
