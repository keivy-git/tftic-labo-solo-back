package be.keivy.fleamarketapp.common.mappers.flea_market;

import be.keivy.fleamarketapp.common.dtos.flea_market.requests.FleaMarketRequest;
import be.keivy.fleamarketapp.common.dtos.flea_market.responses.FleaMarketPagedResponse;
import be.keivy.fleamarketapp.common.dtos.flea_market.responses.FleaMarketResponse;
import be.keivy.fleamarketapp.domain.entities.Address;
import be.keivy.fleamarketapp.domain.entities.FleaMarket;
import be.keivy.fleamarketapp.domain.entities.Organizer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface FleaMarketMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "address.street", target = "address.street")
    @Mapping(source = "address.zipCity.city", target = "address.zipCity.city")
    @Mapping(source = "address.zipCity.zip", target = "address.zipCity.zip")
    FleaMarket toEntity(FleaMarketRequest request, Organizer organizer, Address address);

    @Mapping(source = "active", target = "isActive")
    FleaMarketResponse fromEntity(FleaMarket fleaMarket);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "address.street", target = "address.street")
    @Mapping(source = "address.zipCity.city", target = "address.zipCity.city")
    @Mapping(source = "address.zipCity.zip", target = "address.zipCity.zip")
    void updateEntityFromRequest(FleaMarketRequest request, Address address, @MappingTarget FleaMarket fleaMarket);

    @Mapping(source = "content", target = "fleaMarketResponses", defaultExpression = "java(java.util.Collections.emptyList())")
    @Mapping(source = "size", target = "elementsPerPage")
    FleaMarketPagedResponse fromPage(Page<FleaMarket> page);

}
