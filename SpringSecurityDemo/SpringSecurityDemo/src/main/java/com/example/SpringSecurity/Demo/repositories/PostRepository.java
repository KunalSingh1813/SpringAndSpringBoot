package com.example.SpringSecurity.Demo.repositories;

import com.example.SpringSecurity.Demo.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
