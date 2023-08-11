package com.fastkart.ecomm.FastKart.Ecomm.projection;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Builder
@Setter

public class ProductInformationImpl implements ProductInformation{
    private String name;
    private String description;
    private Long createdAt;
    private float bidAmount;
    private String categoryName;
    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public Long getCreatedAt() {
        return null;
    }

    @Override
    public float getBidAmount() {
        return 0;
    }

    @Override
    public String getCategoryName() {
        return null;
    }
}
