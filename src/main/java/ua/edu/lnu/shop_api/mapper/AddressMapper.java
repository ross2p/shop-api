package ua.edu.lnu.shop_api.mapper;

import org.mapstruct.*;
import ua.edu.lnu.shop_api.dto.address.AddressCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.address.AddressResponse;
import ua.edu.lnu.shop_api.dto.address.AddressUserData;
import ua.edu.lnu.shop_api.entity.Address;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AddressMapper {
    Address toEntity(AddressResponse addressResponse);

    AddressResponse toDto(Address address);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Address partialUpdate(AddressResponse addressResponse, @MappingTarget Address address);

    @Mapping(source = "userId", target = "user.id")
    Address toEntity(AddressCreationUpdateRequest addressCreationUpdateRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "userId", target = "user.id")
    Address partialUpdate(AddressCreationUpdateRequest addressCreationUpdateRequest, @MappingTarget Address address);


    AddressUserData toDto1(Address address);

}