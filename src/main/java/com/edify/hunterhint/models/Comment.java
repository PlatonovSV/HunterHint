package com.edify.hunterhint.models;

import lombok.Data;

import java.util.List;

@Data
public class Comment {
    private String userName;
    private String lastName;
    private String date;
    private String comment;
    private List<ImageLink> links;
}
