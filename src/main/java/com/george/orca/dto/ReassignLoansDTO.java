package com.george.orca.dto;

import com.george.orca.domain.LoanEntity;
import lombok.Data;

import java.util.List;

@Data
public class ReassignLoansDTO {

    private List<Long> loanIds;
    private Long agentId;
    private Long visitorId;

}