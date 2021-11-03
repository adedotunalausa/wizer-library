package com.wizer.wizerbookapp.service;

import com.wizer.wizerbookapp.dto.input.LoginDTO;
import com.wizer.wizerbookapp.dto.input.SignupDTO;
import com.wizer.wizerbookapp.dto.output.BasicResponseDTO;

public interface UserService {

    BasicResponseDTO registerUser(SignupDTO userDetails);

    BasicResponseDTO authenticateUser(LoginDTO userDetails);

}
