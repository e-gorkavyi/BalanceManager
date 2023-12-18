package org.sf.balancemanager.repositories;

import org.sf.balancemanager.entities.Operation;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;

public interface OperationRepo extends CrudRepository<Operation, Long> {

    List<Operation> findAllByUserIdAndCreatedBetween(Long userId, Timestamp startDate, Timestamp endDate);
}
