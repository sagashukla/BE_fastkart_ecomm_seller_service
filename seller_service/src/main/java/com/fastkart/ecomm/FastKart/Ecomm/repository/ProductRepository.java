package com.fastkart.ecomm.FastKart.Ecomm.repository;

import com.fastkart.ecomm.FastKart.Ecomm.entity.Product;
import com.fastkart.ecomm.FastKart.Ecomm.projection.ProductInformation;
import com.fastkart.ecomm.FastKart.Ecomm.projection.ProductWithBid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(
            value = "SELECT p.name as name, p.description as description, p.created_at as createdAt, u.amount as bidAmount, c.category_name as categoryName FROM fastkart.product as p INNER JOIN fastkart.bid as u on u.bid_id = p.id INNER JOIN fastkart.category as c on c.category_id = p.category_id where seller_id = ?1",
            nativeQuery = true)
    List<ProductInformation> getProductsBySeller(int sellerId);
    Optional<Product> findById(Integer id);

    @Query(
            value = "WITH product_details AS ( SELECT p.id AS id, p.name AS name, p.minimum_bid_amount AS mimimumBidAmount, CONCAT(u.first_name, \" \", u.last_name) AS sellerName, cat.category_name AS categoryName FROM fastkart.product AS p INNER JOIN fastkart.user AS u ON p.seller_id = u.id INNER JOIN fastkart.category AS cat ON cat.category_id = p.category_id WHERE p.id = ?1 ), bid_details AS ( SELECT p.id AS b_product_id, b.amount AS bidAmount, b.created_at AS bidCreatedAt, CONCAT(u.first_name, \" \", u.last_name) AS bidderName FROM fastkart.product AS p INNER JOIN fastkart.bid AS b ON p.id = b.product_id INNER JOIN fastkart.user AS u ON u.id = b.buyer_id WHERE p.id = ?1 ) SELECT pd.id, pd.name, pd.mimimumBidAmount, pd.sellerName, pd.categoryName, COALESCE(bd.bidAmount, 0) AS bidAmount, COALESCE(bd.bidCreatedAt, 0) AS bidCreatedAt, COALESCE(bd.bidderName, 'Unknown') AS bidderName FROM product_details AS pd LEFT JOIN bid_details AS bd ON pd.id = bd.b_product_id",
            nativeQuery = true)
    List<ProductWithBid> getProductWithBids(int sellerId);
}
