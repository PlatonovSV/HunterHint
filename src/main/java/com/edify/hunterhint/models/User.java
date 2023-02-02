package com.edify.hunterhint.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "user_data")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    @Column(name = "name_id")
    private int nameId;
    @Column(name = "last_name")
    private String lastLame;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "hash_value")
    private String hashValue;
    @Column(name = "access_level")
    private int accessLevel;
    @Transient
    private String nameSrt;
    @Transient
    private String accessLevelStr;
    public boolean isAdmin() {
        if (accessLevel == 0) {
            return true;
        }
        return false;
    }

    public boolean isOwner() {
        if (accessLevel == 1) {
            return true;
        }
        return false;
    }

    public boolean isHunter() {
        if (accessLevel == -1) {
            return true;
        }
        return false;
    }

    public boolean isOwnerOrAdmin() {
        if (accessLevel != -1) {
            return true;
        }
        return false;
    }

    public String getIdStr() {
        return ""+id;
    }

}
