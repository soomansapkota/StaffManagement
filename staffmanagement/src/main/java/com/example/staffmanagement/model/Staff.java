package com.example.staffmanagement.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer staffId;

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String staffName;

    @OneToOne(cascade = CascadeType.ALL)
    private String staffAddress;
    private String staffPhone;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Invalid email address")
    private String staffEmail;

    private String staffPassword;
}