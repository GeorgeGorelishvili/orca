package com.george.orca.dto;

import lombok.Data;

import java.util.List;

@Data
public class AssignRequestToArchiveDTO {

     List<Long> loanIds;
     String archiveStatus;
     String archiveReason;
}
