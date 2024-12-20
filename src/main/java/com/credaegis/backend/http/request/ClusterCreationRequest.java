package com.credaegis.backend.http.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClusterCreationRequest {

    @NotBlank(message = "name cannot be empty")
    private String clusterName;

    @NotBlank(message = "name cannot be empty")
    private String adminName;

    @Email(message = "Please enter a valid email")
    @NotBlank(message = "email cannot be empty")
    private String adminEmail;
}
