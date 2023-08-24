package com.fastkart.ecomm.FastKart.Ecomm.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fastkart.ecomm.FastKart.Ecomm.repository.CategoryRepository;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OptimisticLock;
import org.hibernate.annotations.OptimisticLockType;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition="text")
    private String description;

    @Column(name = "minimum_bid_amount")
    private float minBidAmount;

    @Column(name = "seller_id")
    private Integer sellerId;

    @Column(name = "created_at")
    private Long createAt;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
}
