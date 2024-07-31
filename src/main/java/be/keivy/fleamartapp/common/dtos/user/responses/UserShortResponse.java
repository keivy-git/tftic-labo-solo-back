package be.keivy.fleamartapp.common.dtos.user.responses;

public record UserShortResponse (
        Long id,
        String email,
        String firstName,
        String lastName,
        String phoneNumber
) { }
