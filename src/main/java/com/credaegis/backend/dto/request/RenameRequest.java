package com.credaegis.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RenameRequest {

    @NotBlank(message = "The new name should not be blank")
    private String newName;
}