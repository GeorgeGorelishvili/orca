package com.george.orca.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ATTACHED_FILES")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttachedFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(name = "loan_id")
    private Long loanId;

    @Column(name = "name")
    private String name;

    @Column(name = "file_name")
    private String originalFileName;

    @Column(name = "original_name")
    private String uploadingFileName;

}
