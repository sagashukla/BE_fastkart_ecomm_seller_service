package com.fastkart.ecomm.FastKart.Ecomm.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AddProductRequest {
    private String name;
    private String description;
    private float minBidAmount;
    private String category;
    private Integer sellerId;
}
