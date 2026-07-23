package ec.edu.ups.icc.labevaluation.supplies.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ec.edu.ups.icc.labevaluation.core.exceptions.domain.NotFoundException;
import ec.edu.ups.icc.labevaluation.supplies.dtos.CreateSupplyDto;
import ec.edu.ups.icc.labevaluation.supplies.dtos.SupplyResponseDto;
import ec.edu.ups.icc.labevaluation.supplies.dtos.UpdateSupplyQuantityDto;
import ec.edu.ups.icc.labevaluation.supplies.entities.SupplyEntity;
import ec.edu.ups.icc.labevaluation.supplies.mappers.SupplyMapper;
import ec.edu.ups.icc.labevaluation.supplies.repositories.SupplyRepository;

@Service
public class SupplyServiceImpl implements SupplyService {

    private final SupplyRepository repository;

    public SupplyServiceImpl(SupplyRepository repository) {
        this.repository = repository;
    }

    @Override
    public SupplyResponseDto create(CreateSupplyDto dto) {
        // if (repository.existsByNameIgnoreCaseAndDeletedFalse(dto.getName())) {
        // }

        SupplyEntity entity = new SupplyEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setQuantity(dto.getQuantity());
        entity.setMinimumStock(dto.getMinimumStock());
        entity.setUnitPrice(dto.getUnitPrice());
        entity.setActive(true);
        entity.setDeleted(false);

        SupplyEntity saved = repository.save(entity);
        return SupplyMapper.toDto(saved);
    }

    @Override
    public List<SupplyResponseDto> getLowStock(Integer maxQuantity) {
        List<SupplyEntity> supplies = repository
                .findByActiveTrueAndDeletedFalseAndQuantityLessThanOrderByQuantityAsc(maxQuantity);

        return supplies.stream()
                .map(SupplyMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SupplyResponseDto updateQuantity(Long id, UpdateSupplyQuantityDto dto) {
        SupplyEntity entity = repository.findById(id)
                .filter(s -> !s.isDeleted())
                .orElseThrow(() -> new NotFoundException("SUPPLY_NOT_FOUND", "Supply not found"));

        entity.setQuantity(dto.getQuantity());
        SupplyEntity saved = repository.save(entity);
        return SupplyMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        SupplyEntity entity = repository.findById(id)
                .filter(s -> !s.isDeleted())
                .orElseThrow(() -> new NotFoundException("SUPPLY_NOT_FOUND", "Supply not found"));

        // if (entity.getQuantity() > 0) {}

        entity.setDeleted(true);
        entity.setActive(false);
        repository.save(entity);
    }
}
