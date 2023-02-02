package com.edify.hunterhint.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "resources_type_dir")
public class HuntingResources {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "resources_type_id")
    private int id;
    @Column(name = "resources_type_name")
    private String name;
}
