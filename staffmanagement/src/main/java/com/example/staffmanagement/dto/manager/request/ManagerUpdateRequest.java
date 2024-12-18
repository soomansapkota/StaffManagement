package com.example.staffmanagement.dto.manager.request;

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
public class ManagerUpdateRequest {
    @NotNull
    private String managerId;
    private String name;
    @Email
    private String email;
}
