package com.myshopping.user_service.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myshopping.user_service.entity.UserRegistration;

@Repository
public interface UserRepository extends JpaRepository<UserRegistration, UUID> {

	Optional<UserRegistration> findByEmail(String email);

}
