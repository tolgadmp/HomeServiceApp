package com.finalproject.homeservice.repository;

import com.finalproject.homeservice.entity.Address;
import com.finalproject.homeservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAddressesByUser(User user);
}
