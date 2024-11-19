package ua.edu.lnu.shop_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ua.edu.lnu.shop_api.dto.address.AddressCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.address.AddressResponse;
import ua.edu.lnu.shop_api.dto.address.AddressUserData;
import ua.edu.lnu.shop_api.dto.auth.DefaultUserDetails;
import ua.edu.lnu.shop_api.entity.Address;
import ua.edu.lnu.shop_api.entity.Product;
import ua.edu.lnu.shop_api.service.AddressService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AddressController {
    private final AddressService addressService;

    @GetMapping("/addresses")
    public ResponseEntity<Page<AddressResponse>> findAll(@RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
                                                         @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                         @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
                                                         @AuthenticationPrincipal DefaultUserDetails userDetails) {

        Page<AddressResponse> products = addressService.findAll(userDetails.getId() ,PageRequest.of(offset, pageSize, Sort.by(sortBy)));
        return ResponseEntity.ok(products);
    }

    @GetMapping("/addresses/{addressId}")
    public ResponseEntity<AddressUserData> findById(@PathVariable(value = "addressId") UUID addressId) {
        AddressUserData address = addressService.findDtoById(addressId);
        return ResponseEntity.ok(address);
    }

    @PostMapping("/addresses")
    public ResponseEntity<Address> create(@RequestBody AddressCreationUpdateRequest address) {
        Address createdAddress = addressService.create(address);
        return ResponseEntity.ok(createdAddress);
    }

    @PutMapping("/addresses/{addressId}")
    public ResponseEntity<Address> update(@PathVariable(value = "addressId") UUID addressId,
                                          @RequestBody AddressCreationUpdateRequest address) {
        Address updatedAddress = addressService.update(addressId, address);
        return ResponseEntity.ok(updatedAddress);
    }
}
