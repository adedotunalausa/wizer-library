package com.wizer.wizerbookapp.dto.output;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponseDTO {
    private String token;
    private String type = "Bearer";
    private String email;
    private List<String> roles;

    public JwtResponseDTO(String accessToken, String email, List<String> roles) {
        this.token = accessToken;
        this.email = email;
        this.roles = roles;
    }

}
