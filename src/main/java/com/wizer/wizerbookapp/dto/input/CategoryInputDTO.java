package com.wizer.wizerbookapp.dto.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CategoryInputDTO {

    @NotBlank(message = "Category name cannot be blank")
    @Size(min = 3, max = 50)
    private String name;

    private String description;
}
