package com.edify.hunterhint.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "company_name_dir")
public class CompanyName {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "company_id")
    private int id;
    @Column(name = "company_name")
    private String name;
}
