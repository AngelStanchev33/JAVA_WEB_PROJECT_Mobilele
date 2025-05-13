package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.model.bindingModel.UserRegisterBindingModel;
import bg.softuni.mobilele.model.dto.UserRoleDto;
import bg.softuni.mobilele.model.entity.UserRoleEntity;
import bg.softuni.mobilele.model.entity.enums.UserRoleEnum;
import bg.softuni.mobilele.model.serviceModel.UserRegisterServiceModel;
import bg.softuni.mobilele.repository.RoleRepository;
import bg.softuni.mobilele.mapper.UserRoleMapper;
import bg.softuni.mobilele.mapper.UserRegistrationMapper;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleMapper userRoleMapper;
    private final UserRegistrationMapper userRegistrationMapper;
    private final ModelMapper mapper;

    public UserService(UserRepository userRepository,
            RoleRepository roleRepository,
            UserRoleMapper userRoleMapper,
            UserRegistrationMapper userRegistrationMapper,
            PasswordEncoder passwordEncoder,
            ModelMapper mapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleMapper = userRoleMapper;
        this.userRegistrationMapper = userRegistrationMapper;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().getUserRole().name())
                .build();
    }

    public void registerUser(UserRegisterBindingModel userRegisterBindingModel) {
        UserRoleEntity userRoleEntity = getRoleEntity(userRegisterBindingModel.getRole());
        UserRegisterServiceModel userRegisterServiceModel = userRegistrationMapper
                .map(userRegisterBindingModel, userRoleEntity);

        userRegisterServiceModel.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));

        UserEntity entity = mapper.map(userRegisterServiceModel, UserEntity.class);

        userRepository.save(entity);

    }

    public UserRoleDto getAllRoles() {
        List<UserRoleEntity> roles = roleRepository.findAll();
        return userRoleMapper.map(roles);
    }

    public UserRoleEntity getRoleEntity(String role) {
        return roleRepository.findByUserRole(UserRoleEnum.valueOf(role));
    }

}

