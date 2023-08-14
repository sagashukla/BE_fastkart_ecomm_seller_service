package com.fastkart.ecomm.FastKart.Ecomm.projection;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ProductWithBidImpl implements ProductWithBid{
    private int id;
    private String description;
    private Long bidCreatedAt;
    private float minimumBidAmount;
    private String name;
    private float bidAmount;
    private String sellerName;
    private String bidderName;
    private String categoryName;
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Long getBidCreatedAt() {
        return bidCreatedAt;
    }

    @Override
    public float getMimimumBidAmount() {
        return minimumBidAmount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getBidAmount() {
        return bidAmount;
    }

    @Override
    public String getSellerName() {
        return sellerName;
    }

    @Override
    public String getBidderName() {
        return bidderName;
    }

    @Override
    public String getCategoryName() {
        return categoryName;
    }
}
