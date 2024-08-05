package be.keivy.fleamarketapp.common.dtos.registration.responses;

import be.keivy.fleamarketapp.common.dtos.flea_market.responses.FleaMarketResponse;
import be.keivy.fleamarketapp.common.dtos.user.responses.SecondHandDealerResponse;
import be.keivy.fleamarketapp.domain.enums.RegistrationStatus;

import java.time.LocalDateTime;

public record RegistrationResponse(

        Long id,
        LocalDateTime applyDate,
        FleaMarketResponse fleaMarketResponse,
        SecondHandDealerResponse secondHandDealerResponse,
        RegistrationStatus registrationStatus
) { }
