package be.keivy.fleamarketapp.common.dtos.flea_market.responses;

import be.keivy.fleamarketapp.domain.entities.Address;
import be.keivy.fleamarketapp.domain.entities.Organizer;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record FleaMarketResponse(
        String title,
        String description,
        String shortDescription,
        String urlPicture,
        LocalDate dateBegin,
        LocalDate dateEnd,
        LocalDateTime createdAt,
        Boolean isActive,
        int pricePerMeter,
        int locationPrice,
        int numberOfPlaces,
        Address address,
        Organizer organizer
        ) { }
