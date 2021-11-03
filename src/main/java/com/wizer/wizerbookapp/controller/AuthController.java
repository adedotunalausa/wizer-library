package com.wizer.wizerbookapp.controller;

import com.wizer.wizerbookapp.dto.input.LoginDTO;
import com.wizer.wizerbookapp.dto.input.SignupDTO;
import com.wizer.wizerbookapp.dto.output.BasicResponseDTO;
import com.wizer.wizerbookapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController extends Controller {

    private final UserService userService;

    @PostMapping("/login")
    public BasicResponseDTO login(@Valid @RequestBody LoginDTO userDetails) {
        return responseWithUpdatedHttpStatus(userService.authenticateUser(userDetails));
    }

    @PostMapping("/signup")
    public BasicResponseDTO register(@Valid @RequestBody SignupDTO userDetails) {
        return responseWithUpdatedHttpStatus(userService.registerUser(userDetails));
    }
}
