package com.finalproject.homeservice.payload.request;

import com.finalproject.homeservice.entity.Role;
import com.finalproject.homeservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminRequestDto {

    private String email;
    private String password;

    private Role role;

    public static AdminRequestDto mapEntityToRequestDto(User user){
        return AdminRequestDto.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

    public static User mapRequestDtoToEntity(AdminRequestDto requestDto){
        return User.builder()
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .role(requestDto.getRole())
                .build();
    }

}
