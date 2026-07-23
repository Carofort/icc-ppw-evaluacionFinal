package ec.edu.ups.icc.labevaluation.supplies.dtos;

import java.math.BigDecimal;

public record SupplyResponseDto(
                Long id,
                String name,
                String description,
                Integer quantity,
                Integer minimumStock,
                BigDecimal unitPrice,
                Boolean active) {

        public SupplyResponseDto(Long id, String name, String description, Integer quantity, Integer minimumStock,
                        BigDecimal unitPrice, Boolean active) {
                this.id = id;
                this.name = name;
                this.description = description;
                this.quantity = quantity;
                this.minimumStock = minimumStock;
                this.unitPrice = unitPrice;
                this.active = active;
        }

        public Long getId() {
                return id;
        }

        public String getName() {
                return name;
        }

        public String getDescription() {
                return description;
        }

        public Integer getQuantity() {
                return quantity;
        }

        public Integer getMinimumStock() {
                return minimumStock;
        }

        public BigDecimal getUnitPrice() {
                return unitPrice;
        }

        public Boolean isActive() {
                return active;
        }

}