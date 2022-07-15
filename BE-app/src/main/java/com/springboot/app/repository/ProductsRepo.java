package com.springboot.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.app.model.Products;

@Repository
public interface ProductsRepo extends JpaRepository<Products, Integer> {

	@Query("SELECT p FROM Products p where catid = :catid")
	List<Products> getProductsbyIdcategory(@Param("id") Integer id);

	List<Products> findByName(String name);

	Products findByIdProduct(Integer id);
}
