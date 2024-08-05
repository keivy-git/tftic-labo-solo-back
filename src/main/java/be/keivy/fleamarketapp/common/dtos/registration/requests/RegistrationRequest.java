package be.keivy.fleamarketapp.common.dtos.registration.requests;

import jakarta.validation.constraints.NotNull;

public record RegistrationRequest(

        @NotNull(message = "The registration id is required")
        Long registrationId
) {
}
