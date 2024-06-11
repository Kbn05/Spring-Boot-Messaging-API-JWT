package com.kbnproject.parcialmoviles.parcialfinal.repository;

import com.kbnproject.parcialmoviles.parcialfinal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmailAndPassword(String email, String password);

    Optional<User> findByEmail(String username);
}
