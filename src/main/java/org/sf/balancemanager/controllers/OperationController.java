package org.sf.balancemanager.controllers;

import lombok.RequiredArgsConstructor;
import org.sf.balancemanager.entities.Operation;
import org.sf.balancemanager.entities.OperationDTO;
import org.sf.balancemanager.services.OperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.sql.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class OperationController {

    private final OperationService operationService;

    @GetMapping("/getOperationList")
    public ResponseEntity<List<OperationDTO>> getOperationList (@RequestParam
                                                                 Long userId,
                                                                @RequestParam(required = false)
                                                                 LocalDateTime startDate,
                                                                @RequestParam(required = false)
                                                                 LocalDateTime endDate) {

        Timestamp startDateTimestamp;
        Timestamp endDateTimestamp;

        if (startDate == null) {
            startDateTimestamp = new Timestamp(0L);
        } else {
            startDateTimestamp = Timestamp.valueOf(startDate);
        }
        if (endDate == null) {
            endDateTimestamp = new Timestamp(Long.MAX_VALUE / 1000);
        } else {
            endDateTimestamp = Timestamp.valueOf(endDate);
        }

        return ResponseEntity.ok(operationService.getOperations(userId, startDateTimestamp, endDateTimestamp));
    }
}
