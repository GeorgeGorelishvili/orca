package com.george.orca.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ASSIGN_REQUEST_REASONS")
@NoArgsConstructor
@AllArgsConstructor
public class AssignRequestReasonsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;


    @Column(name = "reason")
    private String reason;

}
