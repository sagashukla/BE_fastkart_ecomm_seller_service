package com.fastkart.ecomm.FastKart.Ecomm.projection;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ProductWithBidImpl implements ProductWithBid{
    private int id;
    private Long bidCreatedAt;
    private float minimumBidAmount;
    private String name;
    private float bidAmount;
    private String sellerName;
    private String bidderName;
    private String categoryName;
    @Override
    public Integer getId() {
        return null;
    }

    @Override
    public Long getBidCreatedAt() {
        return null;
    }

    @Override
    public float getMimimumBidAmount() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public float getBidAmount() {
        return 0;
    }

    @Override
    public String getSellerName() {
        return null;
    }

    @Override
    public String getBidderName() {
        return null;
    }

    @Override
    public String getCategoryName() {
        return null;
    }
}
