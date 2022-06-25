package com.finalproject.homeservice.repository;

import com.finalproject.homeservice.entity.Role;
import com.finalproject.homeservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findRoleById(long id);

    Role findRoleByName(String roleName);
}
