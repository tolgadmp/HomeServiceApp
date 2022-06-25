package com.finalproject.homeservice.payload.request;

import com.finalproject.homeservice.entity.Address;
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
public class RegularUserRequestDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;

    private List<Address> addressList;
    private Role role;

    public static RegularUserRequestDto mapEntityToRequestDto(User user){
        return RegularUserRequestDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .phone(user.getPhone())
                .addressList(user.getAddresses())
                .role(user.getRole())
                .build();
    }

    public static User mapRequestDtoToEntity(RegularUserRequestDto requestDto){
        return User.builder()
                .firstName(requestDto.getFirstName())
                .lastName(requestDto.getLastName())
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .phone(requestDto.getPhone())
                .addresses(requestDto.getAddressList())
                .role(requestDto.getRole())
                .build();
    }
}
