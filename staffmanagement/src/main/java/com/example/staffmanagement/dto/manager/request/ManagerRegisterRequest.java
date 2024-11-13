package com.example.staffmanagement.dto.manager.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManagerRegisterRequest {
    @NotNull(message = "Manager Id cannot be empty")
    private String managerId;
    private String name;
    @NotNull(message = "Password cannot be empty")
    @Size(min=6,message = "Password must be at least of 6 characters")
    private String password;
    @Email(message = "Email should be Valid")
    private String email;
}
