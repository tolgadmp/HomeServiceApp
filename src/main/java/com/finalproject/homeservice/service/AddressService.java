package com.finalproject.homeservice.service;

import com.finalproject.homeservice.entity.Address;
import com.finalproject.homeservice.entity.User;
import com.finalproject.homeservice.payload.request.AddressRequestDto;
import com.finalproject.homeservice.payload.response.AddressResponseDto;
import com.finalproject.homeservice.repository.AddressRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserService userService;

    public AddressService(AddressRepository addressRepository,
                          @Lazy UserService userService) {
        this.addressRepository = addressRepository;
        this.userService = userService;
    }

    public void createAddress(AddressRequestDto requestDto, String email){
        Address address = AddressRequestDto.mapRequestDtoToEntity(requestDto);
        User user = userService.getUserByEmail(email);
        address.setUser(user);
        addressRepository.save(address);
    }

    public List<AddressResponseDto> getAddressesByUser(String email){
        User user = userService.getUserByEmail(email);
        List<Address> addresses = addressRepository.findAddressesByUser(user);

        return addresses
                .stream()
                .map(address -> AddressResponseDto.mapEntityToResponseDto(address))
                .collect(Collectors.toList());
    }


    public void updateAddress(long id ,AddressRequestDto requestDto){
        Address address = addressRepository.getById(id);
        address.setAddress(requestDto.getAddress());
        address.setDistinc(requestDto.getDistinc());
        address.setCity(requestDto.getCity());
        addressRepository.save(address);
    }

    public void deleteAddress(long id){
        Address address = addressRepository.getById(id);
        addressRepository.delete(address);
    }
}
