package com.edify.hunterhint.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "municipal_district_dir")
public class MunicipalDistrict {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "municipal_district_id")
    private int id;
    @Column(name = "district_name")
    private String name;
    @Column(name = "region_code")
    private short region;
}
