package com.finalproject.homeservice.payload.request;

import com.finalproject.homeservice.entity.Address;
import com.finalproject.homeservice.entity.Expertise;
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
public class ProfessionalUserRequestDto {

    private String companyName;
    private String email;
    private String password;
    private String phone;
    private Expertise expertise;

    private List<Address> addressList;
    private Role role;

    public static ProfessionalUserRequestDto mapEntityToRequestDto(User user){
        return ProfessionalUserRequestDto.builder()
                .companyName(user.getCompanyName())
                .email(user.getEmail())
                .password(user.getPassword())
                .phone(user.getPhone())
                .addressList(user.getAddresses())
                .expertise(user.getExpertise())
                .role(user.getRole())
                .build();
    }

    public static User mapRequestDtoToEntity(ProfessionalUserRequestDto requestDto){
        return User.builder()
                .companyName(requestDto.getCompanyName())
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .phone(requestDto.getPhone())
                .addresses(requestDto.getAddressList())
                .expertise(requestDto.getExpertise())
                .role(requestDto.getRole())
                .build();
    }

}
