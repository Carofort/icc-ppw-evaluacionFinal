package ec.edu.ups.icc.labevaluation.supplies.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.ups.icc.labevaluation.supplies.dtos.CreateSupplyDto;
import ec.edu.ups.icc.labevaluation.supplies.dtos.SupplyResponseDto;
import ec.edu.ups.icc.labevaluation.supplies.dtos.UpdateSupplyQuantityDto;
import ec.edu.ups.icc.labevaluation.supplies.services.SupplyService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/supplies")
public class SupplyController {

    private final SupplyService service;

    public SupplyController(SupplyService service) {
        this.service = service;
    }

    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR')")
    @PostMapping
    public ResponseEntity<SupplyResponseDto> create(@Valid @RequestBody CreateSupplyDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/low-stock")
    public ResponseEntity<List<SupplyResponseDto>> getLowStock(@RequestParam Integer maxQuantity) {
        return ResponseEntity.ok(service.getLowStock(maxQuantity));
    }

    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR')")
    @PatchMapping("/{id}/quantity")
    public ResponseEntity<SupplyResponseDto> updateQuantity(
            @PathVariable Long id, @Valid @RequestBody UpdateSupplyQuantityDto dto) {
        return ResponseEntity.ok(service.updateQuantity(id, dto));
    }

    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build(); 
    }
}
