package ec.edu.ups.icc.labevaluation.supplies.mappers;

import ec.edu.ups.icc.labevaluation.supplies.dtos.SupplyResponseDto;
import ec.edu.ups.icc.labevaluation.supplies.entities.SupplyEntity;

public class SupplyMapper {
    private SupplyMapper() {}

    public static SupplyResponseDto toResponse(SupplyEntity entity) {
        return new SupplyResponseDto
        (entity.getId(), entity.getName(), 
        entity.getDescription(), entity.getQuantity(), 
        entity.getMinimumStock(), entity.getUnitPrice(), entity.isActive());
    }

    public static SupplyResponseDto toDto(SupplyEntity entity) {
        return new SupplyResponseDto(
                entity.getId(), entity.getName(), entity.getDescription(),
                entity.getQuantity(), entity.getMinimumStock(),
                entity.getUnitPrice(), entity.isActive()
        );
    }
}
