package com.example.xml_ex.productshop.repositories;

import com.example.xml_ex.productshop.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u" +
            " WHERE " +
            "   (SELECT COUNT(p) " +
            "    FROM Product p " +
            "    WHERE p.seller = u AND p.buyer IS NOT NULL) > 0" +
            " ORDER BY u.lastName, u.firstName")
    List<User> findAllWithSoldProducts();

    @Query("SELECT u FROM User u" +
            " WHERE " +
            "   (SELECT COUNT(p) " +
            "    FROM Product p " +
            "    WHERE p.seller = u AND p.buyer IS NOT NULL) > 0")
    List<User> findAllWithSoldProductsOrderByCount();


}
