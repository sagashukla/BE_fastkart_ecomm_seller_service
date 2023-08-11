package com.fastkart.ecomm.FastKart.Ecomm.dto;


import lombok.*;


@Builder
@Getter
@AllArgsConstructor
public class AddProductRequest {
    private String name;
    private String description;
    private float minBidAmount;
    private Integer sellerId;
    private Integer categoryId;
}
