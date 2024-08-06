package be.keivy.fleamarketapp.api.controllers;

import be.keivy.fleamarketapp.bll.services.IFleaMarketService;
import be.keivy.fleamarketapp.common.dtos.flea_market.requests.FleaMarketRequest;
import be.keivy.fleamarketapp.common.dtos.flea_market.responses.FleaMarketPagedResponse;
import be.keivy.fleamarketapp.common.dtos.flea_market.responses.FleaMarketResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fleamarkets")
public class FleaMarketController {

    private final IFleaMarketService fleaMarketService;

    @GetMapping
    public ResponseEntity<FleaMarketPagedResponse> getAll(
            @RequestParam Map<String, String> params,
            @RequestParam(defaultValue = "0") int page)
    {
        return ResponseEntity.ok(fleaMarketService.getAll(params, page));
    }

    @GetMapping("/organizer/{id:^[0-9]+$}")
    public ResponseEntity<List<FleaMarketResponse>> getAllByOrganizer(@PathVariable Long id) {
        return ResponseEntity.ok(fleaMarketService.getAllByOrganizer(id));
    }

    @PreAuthorize("hasAnyAuthority('ORGANIZER', 'ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<FleaMarketResponse> createFleaMarket(@RequestBody @Valid FleaMarketRequest request) {
        return ResponseEntity.ok(fleaMarketService.createFleaMarket(request));
    }

    @PreAuthorize("hasAnyAuthority('ORGANIZER', 'ADMIN')")
    @PutMapping("/update/{id:^[0-9]+$}")
    public ResponseEntity<FleaMarketResponse> updateFleaMarket(
            @PathVariable Long id,
            @RequestBody @Valid FleaMarketRequest request) {
        return ResponseEntity.ok(fleaMarketService.updateFleaMarket(id, request));
    }

    @PreAuthorize("hasAnyAuthority('ORGANIZER', 'ADMIN')")
    @DeleteMapping("/delete/{id:^[0-9]+$}")
    public ResponseEntity<FleaMarketResponse> deleteFleaMarket(@PathVariable Long id) {
        return ResponseEntity.ok(fleaMarketService.deleteFleaMarket(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{id:^[0-9]+$}/activate")
    public ResponseEntity<FleaMarketResponse> activateFleaMarket(@PathVariable Long id) {
        return ResponseEntity.ok(fleaMarketService.triggerActive(id, true));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{id:^[0-9]+$}/desactivate")
    public ResponseEntity<FleaMarketResponse> deactivateFleaMarket(@PathVariable Long id) {
        return ResponseEntity.ok(fleaMarketService.triggerActive(id, false));
    }
}
