package com.springboot.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.app.model.AccInfo;

@Repository
public interface AccInfoRepo extends JpaRepository<AccInfo, Integer> {
	@Query("SELECT i FROM AccInfo i WHERE i.phone = :phone")
	public Optional<AccInfo> findByPhone(String phone);
}