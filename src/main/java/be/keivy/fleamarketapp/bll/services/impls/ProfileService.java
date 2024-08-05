package be.keivy.fleamarketapp.bll.services.impls;

import be.keivy.fleamarketapp.bll.services.IAuthService;
import be.keivy.fleamarketapp.bll.services.IProfileService;
import be.keivy.fleamarketapp.bll.services.IUserService;
import be.keivy.fleamarketapp.common.dtos.user.requests.OrganizerUpdateRequest;
import be.keivy.fleamarketapp.common.dtos.user.requests.SecondHandDealerUpdateRequest;
import be.keivy.fleamarketapp.common.dtos.user.requests.UserUpdatePasswordRequest;
import be.keivy.fleamarketapp.common.dtos.user.responses.*;
import be.keivy.fleamarketapp.common.exceptions.InvalidUserTypeException;
import be.keivy.fleamarketapp.common.exceptions.auth.InvalidPasswordException;
import be.keivy.fleamarketapp.common.mappers.user.UserMapper;
import be.keivy.fleamarketapp.dal.repositories.*;
import be.keivy.fleamarketapp.domain.entities.Organizer;
import be.keivy.fleamarketapp.domain.entities.SecondHandDealer;
import be.keivy.fleamarketapp.domain.entities.User;
import be.keivy.fleamarketapp.domain.enums.RegistrationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileService implements IProfileService {

    private final SecondHandDealerRepository secondHandDealerRepository;
    private final OrganizerRepository organizerRepository;
    private final IUserService userService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RegistrationRepository registrationRepository;
    private final IAuthService authService;
    private final PasswordEncoder passwordEncoder;
    private final FleaMarketRepository fleaMarketRepository;


    @Override
    public UserUpdatePasswordResponse userChangePassword(UserUpdatePasswordRequest request) {
        User user = authService.getAuthenticatedUser();

        if (!passwordEncoder.matches(request.oldPassword(), user.getPassword())) {
            throw new InvalidPasswordException();
        }

        user.setPassword(passwordEncoder.encode(request.newPassword()));
        userRepository.save(user);

        return userMapper.fromUserToUserUpdatePassword(user, "Update password success");
    }

    @Override
    public UserResponse disableSelf() {
        User user = authService.getAuthenticatedUser();

        return disableProfile(user);
    }
    @Override
    public UserResponse deleteSelf() {
        User user = authService.getAuthenticatedUser();

        return deleteprofile(user);
    }


    @Override
    public UserResponse disableProfile(User user) {
        user.setEnabled(false);

        if(user instanceof SecondHandDealer secondHandDealer) {
            registrationRepository.updateAllStatusBySecondHandDealerId(secondHandDealer.getId(), RegistrationStatus.CANCELLED);

            return userMapper.fromUser(userRepository.save(user));
        }

        if (user instanceof Organizer organizer) {
            fleaMarketRepository.updateAllActiveFleaMarketByOrganizer(organizer.getId(), false);
            return userMapper.fromUser(userRepository.save(user));
        }
        throw new IllegalStateException("User is not organizer or second hand dealer");
    }

    @Override
    public UserResponse deleteprofile(User user) {

        if (user.isExpired()) {
            throw new IllegalStateException("Profile is already deleted!");
        }

        final String DELETE_STRING = "deleted";

        user.setEmail(UUID.randomUUID().toString());
        user.setFirstName(DELETE_STRING);
        user.setLastName(DELETE_STRING);
        user.setPassword(DELETE_STRING);
        user.setPhoneNumber(DELETE_STRING);
        user.setLocked(true);

        if (user.isEnabled()) {
            disableProfile(user);
        }

        return userMapper.fromUser(userRepository.save(user));

    }

    @Override
    public UserProfileResponse getProfile() {
            User user = authService.getAuthenticatedUser();

            if(user instanceof SecondHandDealer secondHandDealer) {
                return userMapper.fromSecondHandDealerProfile(secondHandDealer);
            }
            if (user instanceof Organizer organizer) {
                return userMapper.fromOrganizerProfile(organizer);
            }
            throw new InvalidUserTypeException();
        }


    @Override
    public SecondHandDealerResponse updateSecondHandDealer(SecondHandDealerUpdateRequest request) {
        User user = authService.getAuthenticatedUser();
        return userService.updateSecondHandDealer(user.getId(), request);
    }

    @Override
    public OrganizerResponse updateOrganizer(OrganizerUpdateRequest request) {
        User user = authService.getAuthenticatedUser();
        return userService.updateOrganizer(user.getId(), request);
    }

    @Override
    public UserResponse deleteUserAsAdmin(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return deleteprofile(user);
    }
}
