package be.keivy.fleamarketapp.bll.services;

import be.keivy.fleamarketapp.common.dtos.flea_market.requests.FleaMarketRequest;
import be.keivy.fleamarketapp.common.dtos.flea_market.responses.FleaMarketPagedResponse;
import be.keivy.fleamarketapp.common.dtos.flea_market.responses.FleaMarketResponse;

import java.util.List;
import java.util.Map;

public interface IFleaMarketService {

    FleaMarketPagedResponse getAll(Map<String, String> params, int page);

    List<FleaMarketResponse> getAllByOrganizer(Long id);

    FleaMarketResponse createFleaMarket(FleaMarketRequest request);

    FleaMarketResponse updateFleaMarket(Long id, FleaMarketRequest request);

    FleaMarketResponse deleteFleaMarket(Long id);

    FleaMarketResponse triggerActive(Long id, boolean isActive);
}
