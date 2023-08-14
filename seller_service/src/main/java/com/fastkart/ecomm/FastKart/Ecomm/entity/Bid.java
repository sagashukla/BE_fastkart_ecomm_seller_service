package com.fastkart.ecomm.FastKart.Ecomm.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Bid")
public class Bid {
    @Id
    @GeneratedValue
    @Column(name = "bid_id")
    private Integer id;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "buyer_id")
    private Integer buyerId;

    @Column(name = "created_at")
    private Long bidCreatedAt;
}