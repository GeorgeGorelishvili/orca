package com.george.orca.dto;

import lombok.Data;

@Data
public class LoanSearchQuery {
    Long id;
    String creditorOrganization;
    String debtorOrganization;
    String creditorPerson;
    String debtorPerson;
}
