package ua.edu.lnu.shop_api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ua.edu.lnu.shop_api.dto.address.AddressCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.address.AddressResponse;
import ua.edu.lnu.shop_api.dto.address.AddressUserData;
import ua.edu.lnu.shop_api.entity.Address;

import java.util.UUID;

public interface AddressService {
    Address create(AddressCreationUpdateRequest address);

    Address update(UUID id, AddressCreationUpdateRequest address);

    void delete(UUID id);

    Address findById(UUID id);
    AddressUserData findDtoById(UUID id);

    Page<AddressResponse> findAll(UUID userId, PageRequest pageRequest);
}