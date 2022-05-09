package com.george.orca.dto;

import com.george.orca.domain.LoanEntity;
import lombok.Data;

@Data
public class LoanEditDTO {
    private LoanEntity loanEntity;
    private Long assignedEmployeeId;

}
