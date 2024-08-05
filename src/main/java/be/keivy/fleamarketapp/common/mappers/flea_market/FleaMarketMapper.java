package be.keivy.fleamarketapp.common.mappers.flea_market;

import be.keivy.fleamarketapp.common.dtos.flea_market.requests.FleaMarketRequest;
import be.keivy.fleamarketapp.common.dtos.flea_market.responses.FleaMarketPagedResponse;
import be.keivy.fleamarketapp.common.dtos.flea_market.responses.FleaMarketResponse;
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
    @Mapping(source = "request.address.street", target = "address.street")
    @Mapping(source = "request.address.zipCity.city", target = "address.zipCity.city")
    @Mapping(source = "request.address.zipCity.zip", target = "address.zipCity.zip")
    FleaMarket toEntity(FleaMarketRequest request, Organizer organizer);

    @Mapping(source = "active", target = "isActive")
    FleaMarketResponse fromEntity(FleaMarket fleaMarket);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "description", target = "description")
    @Mapping(source = "request.address.street", target = "address.street")
    @Mapping(source = "request.address.zipCity.city", target = "address.zipCity.city")
    @Mapping(source = "request.address.zipCity.zip", target = "address.zipCity.zip")
    void updateEntityFromRequest(FleaMarketRequest request, @MappingTarget FleaMarket fleaMarket);

    @Mapping(source = "page.content", target = "fleaMarketResponses", defaultExpression = "java(java.util.Collections.emptyList())")
    @Mapping(source = "page.size", target = "elementsPerPage")
    FleaMarketPagedResponse fromPage(Page<FleaMarket> page);

}
