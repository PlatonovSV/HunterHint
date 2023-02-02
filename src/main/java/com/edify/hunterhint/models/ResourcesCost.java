package com.edify.hunterhint.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "hunting_resources_cost")
public class ResourcesCost {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "hunting_offer_id")
    private int offerId;
    @Column(name = "trophy_value")
    private String trophyValue;
    @Column(name = "resources_cost")
    private int cost;

}
