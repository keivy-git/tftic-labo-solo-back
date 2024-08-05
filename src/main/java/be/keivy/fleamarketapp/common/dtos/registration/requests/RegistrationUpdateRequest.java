package be.keivy.fleamarketapp.common.dtos.registration.requests;

import be.keivy.fleamarketapp.domain.enums.RegistrationStatus;
import jakarta.validation.constraints.NotNull;

public record RegistrationUpdateRequest(
        @NotNull(message = "Application status cannot be null")
        RegistrationStatus registrationStatus
) {}
