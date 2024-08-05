package be.keivy.fleamarketapp.common.dtos.flea_market.responses;

import be.keivy.fleamarketapp.domain.entities.Address;
import be.keivy.fleamarketapp.domain.entities.Organizer;

import java.time.LocalDate;

public record FleaMarketResponse(
        String title,
        String description,
        String urlPicture,
        LocalDate dateBegin,
        LocalDate dateEnd,
        Boolean isActive,
        int pricePerMeter,
        int locationPrice,
        Address address,
        Organizer organizer
        ) { }
