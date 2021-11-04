package com.wizer.wizerbookapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @NotBlank(message = "Book name cannot be blank")
    @Size(min = 2)
    private String name;

    private String description;

    private int quantity;

    @Column(name = "category_id")
    private Long categoryId;

}
