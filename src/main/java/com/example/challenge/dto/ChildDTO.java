package com.example.challenge.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class ChildDTO implements Serializable {

    private Long id;
    private String comment;
    private int rating;
    private Long version;
}
