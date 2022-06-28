package com.springboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.model.Categories;

@Repository
public interface CategoriesRepo extends JpaRepository<Categories, Long>{

}
