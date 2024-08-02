package be.keivy.fleamarketapp.infrastructure.utils;

public class Constants {

    /**
     * Authentifacation
     */
    public static final String AUTH_ENDPOINT = "/auth/";
    public static final String AUTH_HEADER = "Authorization";
    public static final String JWT_TOKEN_PREFIX = "Bearer ";

    /**
     * Roles
     */
    public static final String ADMIN_ROLE = "ADMIN";
    public static final String ORGANIZER_ROLE = "ORGANIZER";
    public static final String SECOND_HAND_DEALER_ROLE = "SECOND_HAND_DEALER";

    /**
     * Pagination
     */
    public static final int PAGE_SIZE = 10;
}
