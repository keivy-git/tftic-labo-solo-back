package be.keivy.fleamarketapp.common.dtos.flea_market.responses;

import java.util.List;

public record FleaMarketPagedResponse (
        List<FleaMarketResponse> fleaMarketResponses,
        Integer elementsPerPage,
        Long totalElements,
        Integer totalPages
){
}
