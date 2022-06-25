package com.finalproject.homeservice.repository;

import com.finalproject.homeservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    User getUserById(long id);

    User getUserByFirstName(String firstName);

    Boolean existsUserByEmail(String email);
}
