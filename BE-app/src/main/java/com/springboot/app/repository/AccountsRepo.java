package com.springboot.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.app.model.Accounts;
import com.springboot.app.model.CartItem;

@Repository
public interface AccountsRepo extends JpaRepository<Accounts, Integer> {

    List<Accounts> findAllByOrderByIdAsc();
    
    Optional <Accounts> findByEmail(String email);
    @Query("Select a.cartItemCollection from Accounts a where a.id = :id ")
    public List<CartItem> findCartById(Integer id);

}
