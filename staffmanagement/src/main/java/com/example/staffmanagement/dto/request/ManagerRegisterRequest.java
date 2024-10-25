package com.example.staffmanagement.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManagerRegisterRequest {
    @NotNull
    private String managerId;
    private String name;
    private String password;
    @Email
    private String email;
}
