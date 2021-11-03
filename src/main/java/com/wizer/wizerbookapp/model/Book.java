package com.wizer.wizerbookapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "books",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        }
)
public class Book extends BaseModel{

    @NotBlank
    @Size(min = 2)
    private String name;

    private String description;

    private int quantity;
}
