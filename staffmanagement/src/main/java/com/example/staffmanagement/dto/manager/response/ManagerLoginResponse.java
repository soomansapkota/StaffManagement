package com.example.staffmanagement.dto.manager.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManagerLoginResponse {
    @NotNull
    private String managerId;
    private String name;
    private String message;
}
