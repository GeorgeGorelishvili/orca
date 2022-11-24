package com.george.orca.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "PROMISES")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromiseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(name = "loan_id")
    private Long loanId;

    @Column(name = "author")
    private String author;

    @Column(name="date")
    private Date date;

}
