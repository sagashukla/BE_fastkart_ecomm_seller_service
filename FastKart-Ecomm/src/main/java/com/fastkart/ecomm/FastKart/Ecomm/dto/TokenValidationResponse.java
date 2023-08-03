package com.fastkart.ecomm.FastKart.Ecomm.dto;

import lombok.*;

@Getter
@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenValidationResponse {
    private String status;
    private boolean isAuthenticated;
    private String methodType;
    private String email;
    private String roleType;
}