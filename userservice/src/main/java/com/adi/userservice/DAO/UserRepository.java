package com.adi.userservice.DAO;

import com.adi.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    public Optional<User> findByEmail(String email);

    Boolean existsByKeyCloakId(String userId);
}
