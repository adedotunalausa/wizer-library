package com.wizer.wizerbookapp.controller;

import com.wizer.wizerbookapp.dto.output.StandardResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;

public class Controller {

    @Autowired
    private HttpServletResponse response;

    <T extends StandardResponseDTO> T responseWithUpdatedHttpStatus (T responseDTO) {
        switch (responseDTO.getStatus()) {
            case CREATED:
                response.setStatus(HttpStatus.CREATED.value());
                break;
            case SUCCESS:
                response.setStatus(HttpStatus.OK.value());
                break;
            case NOT_FOUND:
                response.setStatus(HttpStatus.NOT_FOUND.value());
                break;
            case FORBIDDEN:
                response.setStatus(HttpStatus.FORBIDDEN.value());
            default:
                response.setStatus(HttpStatus.BAD_REQUEST.value());
        }

        return responseDTO;
    }
}
