package com.credaegis.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrganizationInfoDTO {
    private String id;
    private String name;
    private String address;
    private String pincode;
}
