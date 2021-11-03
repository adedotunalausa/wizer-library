package com.wizer.wizerbookapp.dto.input;

import com.wizer.wizerbookapp.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleDTO {

    private RoleType name;
}
