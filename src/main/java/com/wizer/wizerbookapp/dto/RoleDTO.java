package com.wizer.wizerbookapp.dto;

import com.wizer.wizerbookapp.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleDTO {

    private RoleType name;
}
