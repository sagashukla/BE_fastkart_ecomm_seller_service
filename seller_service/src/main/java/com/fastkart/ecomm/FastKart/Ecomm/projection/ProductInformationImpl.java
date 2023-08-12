package com.fastkart.ecomm.FastKart.Ecomm.projection;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Builder
@Setter

public class ProductInformationImpl implements ProductInformation{
    private Integer id;
    private String name;
    private String description;
    private Long createdAt;
    private float maxBidAmount;
    private String categoryName;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Long getCreatedAt() {
        return createdAt;
    }

    @Override
    public float getMaxBidAmount() {
        return maxBidAmount;
    }

    @Override
    public String getCategoryName() {
        return categoryName;
    }
}
