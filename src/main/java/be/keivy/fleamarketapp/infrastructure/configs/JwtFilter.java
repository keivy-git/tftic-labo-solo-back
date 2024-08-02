package be.keivy.fleamarketapp.infrastructure.configs;

import be.keivy.fleamarketapp.infrastructure.utils.Constants;
import be.keivy.fleamarketapp.infrastructure.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;
    private final UserDetailsChecker userDetailsChecker;


    /**
     * Vérifie si la requête ne doit pas être filtrée.
     * La requête ne doit pas être filtrée si le chemin du servlet commence par {@value Constants#AUTH_ENDPOINT}.
     * @param request La requête à vérifier.
     * @return True si la requête ne doit pas être filtrée, false sinon.
     */

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().startsWith(Constants.AUTH_ENDPOINT);
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader(Constants.AUTH_HEADER);

        if (authHeader != null && authHeader.startsWith(Constants.JWT_TOKEN_PREFIX)) {
            String token = authHeader.substring(7);

            if (!token.isBlank() && jwtUtils.validateToken(token)) {
                String username = jwtUtils.getUsername(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                userDetailsChecker.check(userDetails);

                UsernamePasswordAuthenticationToken upt = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                SecurityContextHolder.getContext().setAuthentication(upt);
            }
        }

        filterChain.doFilter(request, response);
    }
}
