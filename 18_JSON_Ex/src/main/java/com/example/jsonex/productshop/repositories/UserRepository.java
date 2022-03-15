package com.example.jsonex.productshop.repositories;

import com.example.jsonex.productshop.entities.users.User;
import com.example.jsonex.productshop.entities.users.UserWithSoldProductsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u" +
            " JOIN u.sellingItems p" +
            " WHERE p.buyer IS NOT NULL")
    List<User> findAllWithSoldProducts();

    @Query("SELECT u FROM User u" +
            " JOIN u.sellingItems p" +
            " WHERE p.buyer IS NOT NULL" +
            " ORDER BY size(u.sellingItems) DESC, u.lastName ASC")
    List<User> findAllWithSoldProductsOrderByCount();
}
