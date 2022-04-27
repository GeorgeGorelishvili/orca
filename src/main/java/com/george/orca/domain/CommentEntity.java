package com.george.orca.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.george.orca.service.CommentService;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
@Entity
@Table(name = "COMMENTS")
public class CommentEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(name="COMMENT", length = 10000)
    private String comment;

    @Column(name = "loan_id")
    private Long loan_id;

}
