package ec.edu.ups.icc.labevaluation.supplies.services;

import java.util.List;

import org.springframework.stereotype.Service;
import ec.edu.ups.icc.labevaluation.supplies.dtos.CreateSupplyDto;
import ec.edu.ups.icc.labevaluation.supplies.dtos.SupplyResponseDto;
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
        //}

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
    
}
