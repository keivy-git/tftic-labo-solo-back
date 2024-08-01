package be.keivy.fleamarketapp.common.dtos.user.responses;

public record UserShortResponse (
        Long id,
        String email,
        String firstName,
        String lastName,
        String phoneNumber
) { }
