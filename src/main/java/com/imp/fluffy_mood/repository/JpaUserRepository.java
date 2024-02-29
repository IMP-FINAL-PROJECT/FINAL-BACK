package com.imp.fluffy_mood.repository;

import com.imp.fluffy_mood.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<User, String> {
}
