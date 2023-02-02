package com.edify.hunterhint.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "region_dir")
public class Region {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "region_code")
    private short code;
    @Column(name = "region_name")
    private String name;

}
