package com.springboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.model.ProductRatings;

@Repository
public interface RatingsRepo extends JpaRepository<ProductRatings, Integer> {
}
