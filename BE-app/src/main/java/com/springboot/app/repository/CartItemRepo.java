package com.springboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.app.model.CartItem;

import java.util.List;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem,Integer> {
   @Query("SELECT c FROM CartItem c WHERE c.accounts.id = :id")
	public List<CartItem> findByIdAccount(Integer id);
}
