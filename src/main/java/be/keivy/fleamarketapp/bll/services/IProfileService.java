package be.keivy.fleamarketapp.bll.services;

import be.keivy.fleamarketapp.common.dtos.user.requests.OrganizerUpdateRequest;
import be.keivy.fleamarketapp.common.dtos.user.requests.SecondHandDealerUpdateRequest;
import be.keivy.fleamarketapp.common.dtos.user.requests.UserUpdatePasswordRequest;
import be.keivy.fleamarketapp.common.dtos.user.responses.*;
import be.keivy.fleamarketapp.domain.entities.User;

public interface IProfileService {

    UserUpdatePasswordResponse userChangePassword(UserUpdatePasswordRequest request);

    UserResponse disableSelf();

    UserResponse deleteSelf();

    UserResponse disableProfile(User user);

    UserResponse deleteprofile(User user);

    UserProfileResponse getProfile();

    SecondHandDealerResponse updateSecondHandDealer(SecondHandDealerUpdateRequest request);

    OrganizerResponse updateOrganizer(OrganizerUpdateRequest request);

    UserResponse deleteUserAsAdmin(Long id);

}
