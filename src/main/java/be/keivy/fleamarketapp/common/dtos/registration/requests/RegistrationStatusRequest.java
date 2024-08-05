package be.keivy.fleamarketapp.common.dtos.registration.requests;

import be.keivy.fleamarketapp.domain.enums.RegistrationStatus;
import jakarta.validation.constraints.NotNull;

public record RegistrationStatusRequest(

        @NotNull(message = "Status is mandatory")
        RegistrationStatus status
) {
}
