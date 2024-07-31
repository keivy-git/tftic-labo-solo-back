package be.keivy.fleamartapp.common.dtos.auth.responses;

import be.keivy.fleamartapp.common.dtos.user.responses.UserShortResponse;
import be.keivy.fleamartapp.domain.entities.Role;
import be.keivy.fleamartapp.domain.entities.User;

import java.util.Set;
import java.util.stream.Collectors;

public record UserTokenResponse(
        UserShortResponse user,
        Set<String> roles,
        String accessToken
) {
    public static UserTokenResponse fromEntityWithToken(User user, String token) {
        return new UserTokenResponse(
                new UserShortResponse(
                        user.getId(),
                        user.getEmail(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getPhoneNumber()
                ),
                user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()),
                token
        );
    }
}
