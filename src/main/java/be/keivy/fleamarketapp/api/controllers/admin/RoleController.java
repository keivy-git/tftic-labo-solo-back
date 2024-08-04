package be.keivy.fleamarketapp.api.controllers.admin;

import be.keivy.fleamarketapp.bll.services.impls.RoleService;
import be.keivy.fleamarketapp.common.dtos.role.requests.RoleRequest;
import be.keivy.fleamarketapp.common.dtos.role.responses.RoleResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin/role")
@PreAuthorize("hasAuthority('ADMIN')")
public class RoleController   {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleResponse>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAll());
    }

    @GetMapping(params = "name")
    public ResponseEntity<RoleResponse> getByName(@RequestParam String name) {
        return ResponseEntity.ok(roleService.getByName(name));
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<RoleResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.getById(id));
    }

    @PostMapping
    public ResponseEntity<RoleResponse> create(@RequestBody @Valid RoleRequest roleRequest) {
        return ResponseEntity.ok(roleService.create(roleRequest));
    }

    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<RoleResponse> update(@PathVariable Long id, @RequestBody @Valid RoleRequest roleRequest) {
        return ResponseEntity.ok(roleService.update(id, roleRequest));
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<RoleResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.delete(id));
    }
}
