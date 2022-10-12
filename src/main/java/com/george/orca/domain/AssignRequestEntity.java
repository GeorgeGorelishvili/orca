package com.george.orca.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "ASSIGN_REQUEST")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(name = "COMMENT", length = 10000)
    private String comment;

    @Column(name = "loan_id")
    private Long loanId;

    @ManyToOne
    private AssignRequestReasonsEntity reason;

    @Column(name = "date")
    private Date date;

    @Column(name = "author")
    private String author;


}
