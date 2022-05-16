package com.george.orca.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.george.orca.service.CommentService;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
@Entity
@Table(name = "COMMENTS")
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CommentEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(name="COMMENT", length = 10000)
    private String comment;

    @Column(name = "loan_id")
    private Long loanId;

    @Column(name = "author")
    private String author;

    @Column(name ="promise")
    private Boolean promise;

    @Column(name="promise_date")
    private Date promiseDate;

}
