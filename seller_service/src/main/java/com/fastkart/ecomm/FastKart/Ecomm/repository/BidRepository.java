package com.fastkart.ecomm.FastKart.Ecomm.repository;

import com.fastkart.ecomm.FastKart.Ecomm.entity.Bid;
import com.fastkart.ecomm.FastKart.Ecomm.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends CrudRepository<Bid, Integer> {
    @Query(
            value = "with product_details as ( SELECT p.id as p_product_id, p.name as product_name, p.minimum_bid_amount as min_bid_amount, CONCAT(u.first_name, \" \", u.last_name) as seller_name, cat.category_name as category_name FROM fastkart.product as p INNER JOIN fastkart.user as u on p.seller_id = u.id INNER JOIN fastkart.category as cat on cat.category_id = p.category_id where p.id = ?1 ), bid_details as ( SELECT p.id as b_product_id, b.amount, CONCAT(u.first_name, \" \", u.last_name) as bidder_name FROM fastkart.product as p INNER JOIN fastkart.bid as b on p.id = b.product_id INNER JOIN fastkart.user as u on u.id = b.buyer_id where p.id = ?1 ) select * from product_details as pd inner join bid_details bd on pd.p_product_id = bd.b_product_id",
            nativeQuery = true)
    public Object[][] getBids(int sellerId);
}
