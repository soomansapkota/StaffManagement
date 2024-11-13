package com.example.staffmanagement.dto.manager.response;

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
public class ManagerRegisterResponse {
    @NotNull(message = "Manager ID cannot be empty")
    private String managerId;
    @Email
    private String email;
    private String name;
    private String message;
}
;