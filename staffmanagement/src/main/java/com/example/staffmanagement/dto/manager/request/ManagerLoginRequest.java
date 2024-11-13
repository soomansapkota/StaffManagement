package com.example.staffmanagement.dto.manager.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManagerLoginRequest {
    @NotNull
    private String managerId;
    private String password;
}
