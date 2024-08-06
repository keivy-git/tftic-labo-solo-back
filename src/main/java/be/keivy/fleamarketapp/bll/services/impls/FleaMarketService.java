package be.keivy.fleamarketapp.bll.services.impls;

import be.keivy.fleamarketapp.bll.services.IFleaMarketService;
import be.keivy.fleamarketapp.common.dtos.flea_market.requests.FleaMarketRequest;
import be.keivy.fleamarketapp.common.dtos.flea_market.responses.FleaMarketPagedResponse;
import be.keivy.fleamarketapp.common.dtos.flea_market.responses.FleaMarketResponse;
import be.keivy.fleamarketapp.common.exceptions.NotAllowedException;
import be.keivy.fleamarketapp.common.exceptions.address.AddressNotFoundException;
import be.keivy.fleamarketapp.common.exceptions.flea_market.FleaMarketNotFoundException;
import be.keivy.fleamarketapp.common.mappers.flea_market.FleaMarketMapper;
import be.keivy.fleamarketapp.dal.repositories.AddressRepository;
import be.keivy.fleamarketapp.dal.repositories.FleaMarketRepository;
import be.keivy.fleamarketapp.dal.repositories.RegistrationRepository;
import be.keivy.fleamarketapp.dal.specifications.FleaMarketSpecifications;
import be.keivy.fleamarketapp.domain.entities.Address;
import be.keivy.fleamarketapp.domain.entities.FleaMarket;
import be.keivy.fleamarketapp.domain.entities.Organizer;
import be.keivy.fleamarketapp.infrastructure.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FleaMarketService implements IFleaMarketService {

    private final FleaMarketRepository fleaMarketRepository;
    private final FleaMarketMapper fleaMarketMapper;
    private final AuthService authService;
    private final AddressRepository addressRepository;
    private final RegistrationRepository registrationRepository;

    @Override
    public FleaMarketPagedResponse getAll(Map<String, String> params, int page) {
        Pageable pageable = PageRequest.of(page, Constants.PAGE_SIZE);

        Page<FleaMarket> pagedFleaMarket = fleaMarketRepository
                .findAll(FleaMarketSpecifications.filterByParams(params), pageable);
        return fleaMarketMapper.fromPage(pagedFleaMarket)   ;
    }

    @Override
    public List<FleaMarketResponse> getAllByOrganizer(Long id) {
        return fleaMarketRepository.findAllByOrganizerId(id)
                .stream()
                .map(fleaMarketMapper::fromEntity)
                .toList();
    }

    @Override
    public FleaMarketResponse createFleaMarket(FleaMarketRequest request) {

        Organizer user = authService.getAuthenticatedOrganizer();

        Address address = addressRepository.findById(request.address().getId())
                .orElseThrow(AddressNotFoundException::new);

        FleaMarket fleaMarket = fleaMarketMapper.toEntity(request, user, address);
        fleaMarket.setDateEnd(LocalDate.now().plusDays(fleaMarket.getActiveDay()));

        return fleaMarketMapper.fromEntity(fleaMarketRepository.save(fleaMarket));
    }

    @Override
    public FleaMarketResponse updateFleaMarket(Long id, FleaMarketRequest request) {

        Organizer user = authService.getAuthenticatedOrganizer();

        FleaMarket fleaMarket= fleaMarketRepository.findByIdAndOrganizerId(id, user.getId())
                .orElseThrow(FleaMarketNotFoundException::new);

        Address address = addressRepository.findById(request.address().getId())
                .orElseThrow(AddressNotFoundException::new);

        fleaMarketMapper.updateEntityFromRequest(request, address, fleaMarket);

        return fleaMarketMapper.fromEntity(fleaMarketRepository.save(fleaMarket));
    }

    @Override
    public FleaMarketResponse deleteFleaMarket(Long id) {

        Organizer organizer = authService.getAuthenticatedOrganizer();

        FleaMarket fleaMarket = fleaMarketRepository.findByIdAndOrganizerId(id, organizer.getId())
                .orElseThrow(FleaMarketNotFoundException::new);

        registrationRepository.deleteAllByOrganizerId(id);
        fleaMarketRepository.deleteById(id);

        return fleaMarketMapper.fromEntity(fleaMarket);
    }

    @Override
    public FleaMarketResponse triggerActive(Long id, boolean isActive) {
        FleaMarket fleaMarket = fleaMarketRepository.findById(id).orElseThrow(FleaMarketNotFoundException::new);

        if (isActive == fleaMarket.isActive()) {
            throw new NotAllowedException(String.format("Flea market field 'isActive' already defined to '%s", isActive));
        }
        fleaMarket.setActive(isActive);
        return fleaMarketMapper.fromEntity(fleaMarketRepository.save(fleaMarket));
    }
}
