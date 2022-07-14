package com.springboot.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.model.Roles;

@Repository
public interface RolesRepo extends JpaRepository<Roles, Integer> {

	Optional<Roles> findByName(String name);

}
