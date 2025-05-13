package bg.softuni.mobilele.mapper;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import bg.softuni.mobilele.model.entity.UserRoleEntity;
import bg.softuni.mobilele.model.dto.UserRoleDto;

@Component
public class UserRoleMapper {
    
    private final ModelMapper modelMapper;

    public UserRoleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    
    public UserRoleDto map(List<UserRoleEntity> roles) {
        UserRoleDto dto = new UserRoleDto();
        dto.setRoles(
            roles.stream()
                 .map(role -> modelMapper.map(role.getUserRole(), String.class))
                 .toList()
        );
        return dto;
    }
}   
