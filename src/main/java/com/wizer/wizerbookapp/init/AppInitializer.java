package com.wizer.wizerbookapp.init;

import com.wizer.wizerbookapp.dto.RoleDTO;
import com.wizer.wizerbookapp.enums.RoleType;
import com.wizer.wizerbookapp.model.Role;
import com.wizer.wizerbookapp.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
public class AppInitializer implements ApplicationRunner {

    private final RoleRepository roleRepository;

    private final ModelMapper modelMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Role role;

        if (roleRepository.findByName(RoleType.ROLE_USER).isEmpty()) {
            role = modelMapper.map(new RoleDTO(RoleType.ROLE_USER), Role.class);
            roleRepository.save(role);
        }
    }
}
