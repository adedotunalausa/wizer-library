package com.wizer.wizerbookapp.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class BookInputDTO {

    @NotBlank(message = "Book name cannot be blank")
    @Size(min = 2)
    private String name;

    private String description;

    private int quantity;

    private Long categoryId;
}
