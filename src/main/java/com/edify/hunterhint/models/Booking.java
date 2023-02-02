
package com.edify.hunterhint.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "hunting_booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private int id;
    @Column(name = "farm_Id")
    private Long farmId;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "hunting_offer_id")
    private Long offerId;
    @Column(name = "booking_data")
    private String information;
    @Column(name = "booking_date_time")
    private Timestamp timestamp;
    @Column(name = "hunt_review")
    private String review;
    @Column(name = "check_in")
    private LocalDate checkIn;
    @Column(name = "leave")
    private LocalDate leave;
    @Transient
    private List<ImageLink> imageLinks;

    @PrePersist
    private void setImage() {
        this.imageLinks = new ArrayList<>();
    }

    @Transient
    private String userName;
    @Transient
    private String farmName;

    public String getIdStr() {
        return "" + id;
    }
}
