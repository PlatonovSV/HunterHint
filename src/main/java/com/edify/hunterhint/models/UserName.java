package com.edify.hunterhint.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "user_name_dir")
public class UserName {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "name_id")
    private int id;
    @Column(name = "user_name")
    private String name;
}