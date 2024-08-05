package be.keivy.fleamarketapp.api.controllers.admin;

import be.keivy.fleamarketapp.bll.services.IProfileService;
import be.keivy.fleamarketapp.bll.services.IUserService;
import be.keivy.fleamarketapp.common.dtos.auth.requests.OrganizerRegisterRequest;
import be.keivy.fleamarketapp.common.dtos.auth.requests.SecondHandDealerRegisterRequest;
import be.keivy.fleamarketapp.common.dtos.auth.responses.UserTokenResponse;
import be.keivy.fleamarketapp.common.dtos.user.requests.OrganizerUpdateRequest;
import be.keivy.fleamarketapp.common.dtos.user.requests.SecondHandDealerUpdateRequest;
import be.keivy.fleamarketapp.common.dtos.user.responses.OrganizerResponse;
import be.keivy.fleamarketapp.common.dtos.user.responses.SecondHandDealerResponse;
import be.keivy.fleamarketapp.common.dtos.user.responses.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/users")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    private final IUserService userService;
    private final IProfileService profileService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/organizers")
    public ResponseEntity<List<OrganizerResponse>> getAllOrganizer() {
        return ResponseEntity.ok(userService.getAllOrganizer());
    }

    @GetMapping("/secondheanddealers")
    public ResponseEntity<List<SecondHandDealerResponse>> getAllSecondHandDealer() {
        return ResponseEntity.ok(userService.getAllSecondHandDealer());
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserResponse> getByMail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getByMail(email));
    }

    @GetMapping("/{phone:^[0-9]+$}")
    public ResponseEntity<UserResponse> getByPhone(@PathVariable String phone) {
        return ResponseEntity.ok(userService.getByPhone(phone));
    }

    @PostMapping("add/organizers")
    public ResponseEntity<OrganizerResponse> addOrganizer(@RequestBody @Valid OrganizerRegisterRequest request) {
        return ResponseEntity.ok(userService.addOrganizer(request));
    }

    @PostMapping("add/secondheanddealers")
    public ResponseEntity<SecondHandDealerResponse> addSecondHandDealer(SecondHandDealerRegisterRequest request) throws RoleNotFoundException {
        return ResponseEntity.ok(userService.addSecondHandDealer(request));
    }

    @PutMapping("update/organizer/{id:^[0-9]+$}")
    public ResponseEntity<OrganizerResponse> updateOrganizer(@PathVariable Long id,
                                             @RequestBody @Valid OrganizerUpdateRequest request) {
        return ResponseEntity.ok(userService.updateOrganizer(id, request));
    }

    @PutMapping("update/secondhanddealer/{id:^[0-9]+$}")
    public ResponseEntity<SecondHandDealerResponse> updateSecondHandDealer(@PathVariable Long id,
                                                           @RequestBody @Valid SecondHandDealerUpdateRequest request) {
        return ResponseEntity.ok(userService.updateSecondHandDealer(id, request));
    }

    @PatchMapping("/{id:^[0-9]+$}/lock")
    public ResponseEntity<UserResponse> triggerLock(@PathVariable  Long id) {
        return ResponseEntity.ok(userService.triggerLock(id, true));
    }

    @PatchMapping("/{id:^[0-9]+$}/unlock")
    public ResponseEntity<UserResponse> triggerUnlock(@PathVariable Long id) {
        return ResponseEntity.ok(userService.triggerLock(id, false));
    }

    @PostMapping("/impersonate/id/{id:^[0-9]+$}")
    public ResponseEntity<UserTokenResponse> impersonateUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.impersonateUserById(id));
    }

    @PostMapping("/impersonate/email/{email}")
    public ResponseEntity<UserTokenResponse> impersonateUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.impersonateUserByEmail(email));
    }
    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(profileService.deleteUserAsAdmin(id));
    }
}
