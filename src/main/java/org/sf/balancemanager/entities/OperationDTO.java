package org.sf.balancemanager.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OperationDTO {
    private LocalDateTime created;
    private OperationType operationType;
    private Double amount;

    public OperationDTO(LocalDateTime created, OperationType operationType, Long amount) {
        this.created = created;
        this.operationType = operationType;
        this.amount = amount.doubleValue();
        this.amount = this.amount / 100;
    }
}
