package com.fastkart.ecomm.FastKart.Ecomm.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Bid")
public class Bid {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "product_id")
    private Integer productId;
}
