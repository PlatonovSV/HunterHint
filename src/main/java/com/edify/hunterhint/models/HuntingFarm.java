package com.edify.hunterhint.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "hunting_farms_data")
public class HuntingFarm {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "hunting_farms_id")
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "company_id")
    private int companyId;

    @Column(name = "region_code")
    private short regionCode;
    @Column(name = "municipal_district_id")
    private int municipalDistrictId;
    @Column(name = "hunting_farms_name")
    private String name;
    @Column(name = "base_coordinate")
    private double[] baseCoordinate;
    @Column(name = "hunting_farms_area")
    private float area;
    @Column(name = "information_hotel")
    private boolean hotel;
    @Column(name = "information_bath")
    private boolean bath;
    @Column(name = "max_number_hunters")
    private short maxNumberHunters;
    @Column(name = "hotel_capacity")
    private short hotelCapacity;
    @Column(name = "accommodation_cost")
    private int accommodationCost;
    @Column(name = "text_description")
    private String description;
    @Transient
    private String regionName;
    @Transient
    private String municipalDistrictName;
    @Transient
    private int minCost;
    @Transient
    private String companyStr;
    @Transient
    private String preview;

    public String getFirstCord() {
        return baseCoordinate[0] + "";
    }
    public String getSecondCord() {
        return baseCoordinate[1] + "";
    }

}
