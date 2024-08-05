package be.keivy.fleamarketapp.common.dtos.registration.responses;

import java.util.List;

public record PageRegistrationsResponse(
        List<RegistrationResponse> registrationResponses,
        Integer elementsPerPage,
        Long totalElements,
        Integer totalPages
) {
}
