package bg.softuni.mobilele.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import bg.softuni.mobilele.model.bindingModel.UserRegisterBindingModel;
import bg.softuni.mobilele.model.entity.UserRoleEntity;
import bg.softuni.mobilele.model.serviceModel.UserRegisterServiceModel;

@Component
public class UserRegistrationMapper {

    private final ModelMapper modelMapper;

    public UserRegistrationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserRegisterServiceModel map(UserRegisterBindingModel bindingModel, UserRoleEntity userRoleEntity) {
        UserRegisterServiceModel serviceModel = modelMapper.map(bindingModel, UserRegisterServiceModel.class);
        serviceModel.setEmail(bindingModel.getUsername() + "@gmail.com");
        serviceModel.setActive(true);
        serviceModel.setRole(userRoleEntity);
        return serviceModel;
    }
}
