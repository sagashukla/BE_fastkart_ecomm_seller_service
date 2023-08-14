package com.fastkart.ecomm.FastKart.Ecomm.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SellerErrorResponse {
    private int status;
    private String message;
    private long timeStamp;
}

