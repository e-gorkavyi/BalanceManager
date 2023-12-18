package org.sf.balancemanager.services;

import org.sf.balancemanager.entities.Operation;
import org.sf.balancemanager.entities.OperationDTO;
import org.sf.balancemanager.repositories.OperationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OperationService {

    private final OperationRepo operationRepo;

    @Autowired
    public OperationService(OperationRepo operationRepo) {
        this.operationRepo = operationRepo;
    }

    public List<OperationDTO> getOperations(Long userId, Timestamp startDate, Timestamp endDate) {
        List<OperationDTO> operationDTOS = new ArrayList<>();
        List<Operation> operations = operationRepo.findAllByUserIdAndCreatedBetween(userId, startDate, endDate);
        for (Operation operation : operations) {
            operationDTOS.add(new OperationDTO(
                    operation.getCreated().toLocalDateTime(),
                    operation.getOperationType(),
                    operation.getAmount()
                    )
            );
        }
        return operationDTOS;
    }
}
