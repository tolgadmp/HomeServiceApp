package com.finalproject.homeservice.service;

import com.finalproject.homeservice.entity.Expertise;
import com.finalproject.homeservice.entity.Role;
import com.finalproject.homeservice.entity.User;
import com.finalproject.homeservice.payload.request.ProfessionalUserRequestDto;
import com.finalproject.homeservice.payload.request.RegularUserRequestDto;
import com.finalproject.homeservice.payload.response.AddressResponseDto;
import com.finalproject.homeservice.payload.response.UserResponseDto;
import com.finalproject.homeservice.repository.ExpertiseRepository;
import com.finalproject.homeservice.repository.RoleRepository;
import com.finalproject.homeservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AddressService addressService;
    private final ExpertiseRepository expertiseRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       AddressService addressService,
                       ExpertiseRepository expertiseRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.addressService = addressService;
        this.expertiseRepository = expertiseRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerRegularUser(RegularUserRequestDto requestDto){
        Role role = roleRepository.findRoleByName("RegularUser");
        User user = RegularUserRequestDto.mapRequestDtoToEntity(requestDto);
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        userRepository.save(user);
    }

    public void registerProfessionalUser(ProfessionalUserRequestDto requestDto){
        Role role = roleRepository.findRoleByName("ProfessionalUser");
        Expertise expertise = expertiseRepository.findExpertiseByExpertiseName("Boyacı");
        User user = ProfessionalUserRequestDto.mapRequestDtoToEntity(requestDto);
        user.setRole(role);
        user.setExpertise(expertise);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        userRepository.save(user);
    }

    public boolean checkEmail(String email){
        return userRepository.existsUserByEmail(email);
    }

    public User getUserByEmail(String email){
        return userRepository.getUserByEmail(email);
    }

    public UserResponseDto getUser(String email){
        User user = userRepository.getUserByEmail(email);
        UserResponseDto userResponseDto = UserResponseDto.mapEntityToResponseDto(user);
        return userResponseDto;
    }

    public UserResponseDto viewProfile(String email){
        User user = userRepository.getUserByEmail(email);
        List<AddressResponseDto> addressList = addressService.getAddressesByUser(email);
        UserResponseDto responseDto = UserResponseDto.mapEntityToResponseDto(user);
        responseDto.setAddresses(addressList);
        return responseDto;
    }

}
