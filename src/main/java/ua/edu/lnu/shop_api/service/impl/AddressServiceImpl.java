package ua.edu.lnu.shop_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.edu.lnu.shop_api.dto.address.AddressCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.address.AddressResponse;
import ua.edu.lnu.shop_api.dto.address.AddressUserData;
import ua.edu.lnu.shop_api.entity.Address;
import ua.edu.lnu.shop_api.mapper.AddressMapper;
import ua.edu.lnu.shop_api.repository.AddressRepository;
import ua.edu.lnu.shop_api.service.AddressService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    public Address create(AddressCreationUpdateRequest address) {
        Address addressToCreate = addressMapper.toEntity(address);
        return addressRepository.save(addressToCreate);
    }

    @Override
    public Address update(UUID id, AddressCreationUpdateRequest address) {
        Address existingAddress = this.findById(id);
        Address addressToUpdate = addressMapper.partialUpdate(address, existingAddress);

        return addressRepository.save(addressToUpdate);
    }

    @Override
    public void delete(UUID id) {
        //todo implement
    }

    @Override
    public Address findById(UUID id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));
    }
    public AddressUserData findDtoById(UUID id) {
        return addressMapper.toDto1(this.findById(id));
    }

    @Override
    public Page<AddressResponse> findAll(UUID userId, PageRequest pageRequest) {
        System.out.println("userId = " + userId);
        return addressRepository.findByUser_Id(userId, pageRequest).map(addressMapper::toDto);
    }
}