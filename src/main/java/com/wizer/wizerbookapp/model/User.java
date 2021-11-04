package com.wizer.wizerbookapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        }
)
public class User extends BaseModel {

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    private String gender;

    private String address;

    private String city;

    private String state;

    private String country;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    @JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(	name = "user_favorite_books",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> favoriteBooks = new HashSet<>();

    public User(String username, String firstname, String lastname,
                String gender, String email, String password) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.email = email;
        this.password = password;
    }

}
