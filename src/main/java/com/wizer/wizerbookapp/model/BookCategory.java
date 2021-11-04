package com.wizer.wizerbookapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "book_categories",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        }
)
public class BookCategory extends BaseModel {

    @NotBlank
    @Size(min = 2)
    private String name;

    private String description;

    @OneToMany(targetEntity = Book.class, cascade = CascadeType.ALL, mappedBy = "categoryId")
    private Set<Book> books;

}
