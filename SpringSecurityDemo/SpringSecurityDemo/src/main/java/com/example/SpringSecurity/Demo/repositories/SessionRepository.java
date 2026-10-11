package com.example.SpringSecurity.Demo.repositories;

import com.example.SpringSecurity.Demo.entities.Session;
import com.example.SpringSecurity.Demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> findByUser(User user);

    Optional<Session> findByRefreshToken(String refreshToken);
}
