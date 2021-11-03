package com.wizer.wizerbookapp.service.ServiceImplementation;

import com.wizer.wizerbookapp.dto.input.LoginDTO;
import com.wizer.wizerbookapp.dto.input.SignupDTO;
import com.wizer.wizerbookapp.dto.output.BasicResponseDTO;
import com.wizer.wizerbookapp.dto.output.JwtResponseDTO;
import com.wizer.wizerbookapp.enums.RoleType;
import com.wizer.wizerbookapp.enums.Status;
import com.wizer.wizerbookapp.model.Role;
import com.wizer.wizerbookapp.model.User;
import com.wizer.wizerbookapp.repository.RoleRepository;
import com.wizer.wizerbookapp.repository.UserRepository;
import com.wizer.wizerbookapp.security.service.UserDetailsImpl;
import com.wizer.wizerbookapp.service.UserService;
import com.wizer.wizerbookapp.util.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    @Override
    public BasicResponseDTO registerUser(SignupDTO userDetails) {

        if (emailExists(userDetails.getEmail())) {
            return new BasicResponseDTO(Status.PRECONDITION_FAILED, "Email is already in use");
        }

        if (usernameExists(userDetails.getUsername())) {
            return new BasicResponseDTO(Status.PRECONDITION_FAILED, "Username is already taken");
        }

        User newUser = createUserObjectFromSignupInfo(userDetails);

        setRolesForNewUser(newUser);

        userRepository.save(newUser);

        return new BasicResponseDTO(Status.CREATED, newUser);

    }

    @Override
    public BasicResponseDTO authenticateUser(LoginDTO userDetails) {

        if (!emailExists(userDetails.getEmail())) {
            return new BasicResponseDTO(Status.NOT_FOUND, "Error: User not found! Make sure email is correct " +
                    "or signup if you don't have an account");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDetails.getEmail(), userDetails.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userInfo = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userInfo.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return new BasicResponseDTO(Status.SUCCESS, new JwtResponseDTO(jwt, userInfo.getUsername(), roles));

    }

    private User createUserObjectFromSignupInfo(SignupDTO userDetails) {
        return new User(
                userDetails.getUsername(),
                userDetails.getFirstname(),
                userDetails.getLastname(),
                userDetails.getGender(),
                userDetails.getEmail(),
                passwordEncoder.encode(userDetails.getPassword())
        );
    }

    private boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    private boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    private void setRolesForNewUser(User newUser) {
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(RoleType.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role not found."));
        roles.add(userRole);
        newUser.setRoles(roles);
    }

}
