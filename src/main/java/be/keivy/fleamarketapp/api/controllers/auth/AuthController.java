package be.keivy.fleamarketapp.api.controllers.auth;

import be.keivy.fleamarketapp.bll.services.IAuthService;
import be.keivy.fleamarketapp.common.dtos.auth.requests.LoginRequest;
import be.keivy.fleamarketapp.common.dtos.auth.requests.OrganizerRegisterRequest;
import be.keivy.fleamarketapp.common.dtos.auth.requests.SecondHandDealerRegisterRequest;
import be.keivy.fleamarketapp.common.dtos.auth.responses.UserTokenResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final IAuthService authService;

    @PreAuthorize("isAnonymous()")
    @PostMapping("/login")
    public ResponseEntity<UserTokenResponse> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/register/organizer")
    public ResponseEntity<UserTokenResponse> registerOrganizer(@RequestBody @Valid OrganizerRegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/register/secondhanddealer")
    public ResponseEntity<UserTokenResponse> registerSecondHandDealer(@RequestBody @Valid SecondHandDealerRegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

}
