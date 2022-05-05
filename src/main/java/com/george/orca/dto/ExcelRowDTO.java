package com.george.orca.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
public class ExcelRowDTO {

//   private String Organization;
//   private String responsiblePerson;
//   private String identificationCode;
//   private String director;
//
//
//
//   private Date incomeDate;
//   private Date startDate;
//
//   private String legalAddress;
//   private String physicalAddress;
//
//
//
//   private Long loanId;
//   private BigDecimal amount;


   //თანამშრომელი
   private Long positionId;

   private String firstname;
   private String lastName;
   private String personalNo;

   private Date birthDate;
   private String phoneNumber;

   private String address;

   private String eMail;
   private String bankAccount;

   private String connectedPerson;
   private String connectedPersonPhone;





}
