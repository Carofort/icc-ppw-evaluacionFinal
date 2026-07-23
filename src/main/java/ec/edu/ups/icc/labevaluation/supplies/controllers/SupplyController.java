package ec.edu.ups.icc.labevaluation.supplies.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.ups.icc.labevaluation.supplies.dtos.CreateSupplyDto;
import ec.edu.ups.icc.labevaluation.supplies.dtos.SupplyResponseDto;
import ec.edu.ups.icc.labevaluation.supplies.services.SupplyService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/supplies")
public class SupplyController {

    private final SupplyService supplyService;

    public SupplyController(SupplyService supplyService) {
        this.supplyService = supplyService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR')")
    @PostMapping
    public ResponseEntity<SupplyResponseDto> create(@Valid @RequestBody CreateSupplyDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(supplyService.create(dto));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/low-stock")
    public ResponseEntity<List<SupplyResponseDto>> getLowStock(@RequestParam Integer maxQuantity) {
        return ResponseEntity.ok(supplyService.getLowStock(maxQuantity));
    }
}
