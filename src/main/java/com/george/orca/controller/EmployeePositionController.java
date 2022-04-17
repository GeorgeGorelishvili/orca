package com.george.orca.controller;

import com.george.orca.domain.EmployeePositionEntity;
import com.george.orca.service.EmployeePositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employeePosition")
@RequiredArgsConstructor
public class EmployeePositionController {

    private final EmployeePositionService employeePositionService;

    @GetMapping("get")
    public ResponseEntity<EmployeePositionEntity> get(@RequestParam(name = "loanId") Long loanId) {
        EmployeePositionEntity employeePosition = employeePositionService.get(loanId);
        return ResponseEntity.ok(employeePosition);
    }

    @GetMapping("add")
    public ResponseEntity<EmployeePositionEntity> add(@RequestParam(name = "employeePositionName") String employeePositionName) {
        EmployeePositionEntity employeePosition = EmployeePositionEntity.builder()
                .positionName(employeePositionName)
                .build();
        employeePosition = employeePositionService.edit(employeePosition);
        return ResponseEntity.ok(employeePosition);
    }

    @PostMapping("edit")
    public ResponseEntity<EmployeePositionEntity> edit(@RequestBody EmployeePositionEntity employeePosition) {
        employeePosition = employeePositionService.edit(employeePosition);
        return ResponseEntity.ok(employeePosition);
    }

    @GetMapping("list")
    public ResponseEntity<List<EmployeePositionEntity>> list() {
        List<EmployeePositionEntity> employeePositions = employeePositionService.list();
        return ResponseEntity.ok(employeePositions);
    }

}
