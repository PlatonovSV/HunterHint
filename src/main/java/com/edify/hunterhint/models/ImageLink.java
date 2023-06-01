package com.edify.hunterhint.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "image_link")
public class ImageLink {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "owner_id")
    private int ownerId;
    public String getSource() {
        return "/images/" + id;
    }
}