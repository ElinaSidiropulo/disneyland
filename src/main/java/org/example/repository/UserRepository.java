package org.example.repository;

import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // тут можно добавлять методы поиска по email и т.п.
}
