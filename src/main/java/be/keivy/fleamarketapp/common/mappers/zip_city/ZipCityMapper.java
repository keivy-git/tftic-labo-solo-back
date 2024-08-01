package be.keivy.fleamarketapp.common.mappers.zip_city;

import be.keivy.fleamarketapp.common.dtos.zip_city.requests.ZipCityRequest;
import be.keivy.fleamarketapp.common.dtos.zip_city.responses.ZipCityResponse;
import be.keivy.fleamarketapp.domain.entities.ZipCity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ZipCityMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ZipCity toEntity(ZipCityRequest request);

    ZipCityResponse fromEntity(ZipCity entity);
}
