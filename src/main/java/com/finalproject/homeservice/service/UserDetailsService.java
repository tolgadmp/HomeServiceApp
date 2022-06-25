package com.finalproject.homeservice.service;

import com.finalproject.homeservice.entity.User;
import com.finalproject.homeservice.payload.CustomUser;
import com.finalproject.homeservice.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(username);

        if(user == null){
            throw new UsernameNotFoundException("Kullanıcı bulunamadı");
        }

        return new CustomUser(user);
    }


}
