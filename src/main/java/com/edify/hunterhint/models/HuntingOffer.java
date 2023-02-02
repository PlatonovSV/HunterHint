package com.edify.hunterhint.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "hunting_offer")
public class HuntingOffer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "hunting_offer_id")
    private int id;
    @Column(name = "hunting_farms_id")
    private int farmId;
    @Column(name = "resources_type_id")
    private int resourcesTypeId;
    @Column(name = "opening_date")
    private LocalDate openingDate;
    @Column(name = "closing_date")
    private LocalDate closingDate;
    @Column(name = "hunting_method_id")
    private int methodId;
    @Column(name = "event_cost")
    private int eventCost;
    @Column(name = "guiding_preference_id")
    private int guidingPreferenceId;
    @Column(name = "offer_description_text")
    private String description;
    @Transient
    private int minCost;
    @Transient
    private String resourcesTypeName;
    @Transient
    private String guidingPreferenceName;
    @Transient
    private String methodName;
}
