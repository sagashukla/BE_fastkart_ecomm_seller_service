package com.fastkart.ecomm.FastKart.Ecomm.projection;

import lombok.*;

public interface ProductWithBid {
    Integer getId();
    Long getBidCreatedAt();
    float getMimimumBidAmount();
    String getName();
    float getBidAmount();
    String getSellerName();
    String getBidderName();
    String getCategoryName();
}
