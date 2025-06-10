package bg.softuni.mobilele.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import bg.softuni.mobilele.model.dto.RegisterDto;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.entity.UserRoleEntity;
import bg.softuni.mobilele.model.enums.RoleEnum;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.repository.UserRoleRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, ModelMapper modelMapper,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;

    }

    public void register(RegisterDto registerDto) {

        UserEntity user = modelMapper.map(registerDto, UserEntity.class);

        List<UserRoleEntity> userRoles = registerDto.getRoles()
                .stream()
                .map(userRoleName -> userRoleRepository.findByName(userRoleName).orElse(null))
                .collect(Collectors.toList());

        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setActive(true);
        user.setRoles(userRoles);
        user.setCreated(LocalDateTime.now());

        userRepository.save(user);
    }

    public List<RoleEnum> getRoles() {
        return List.of(RoleEnum.values());
    }

}
