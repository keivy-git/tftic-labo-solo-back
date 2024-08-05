package be.keivy.fleamarketapp.common.mappers.registration;

import be.keivy.fleamarketapp.common.dtos.registration.requests.RegistrationUpdateRequest;
import be.keivy.fleamarketapp.common.dtos.registration.responses.PageRegistrationsResponse;
import be.keivy.fleamarketapp.common.dtos.registration.responses.RegistrationResponse;
import be.keivy.fleamarketapp.domain.entities.Registration;
import be.keivy.fleamarketapp.domain.entities.SecondHandDealer;
import be.keivy.fleamarketapp.domain.enums.RegistrationStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {

    @Mapping(target = "id", ignore= true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "secondHandDealer", target = "secondHandDealer")
    @Mapping(source = "registrationStatus", target = "registrationStatus")
    Registration toEntity(
            LocalDateTime applyDate,
            SecondHandDealer secondHandDealer,
            RegistrationStatus registrationStatus
    );

    RegistrationResponse fromEntity(Registration registration);

    @Mapping(target = "id", ignore= true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "registrationStatus", target = "registrationStatus")
    void updateEntityFromRequest(RegistrationUpdateRequest request, @MappingTarget Registration registration);

    @Mapping(source = "page.content", target = "registrationResponses", defaultExpression = "java(java.util.Collections.emptyList())")
    @Mapping(source = "page.size", target = "elementsPerPage")
    PageRegistrationsResponse fromPage(Page<Registration> page);

}
