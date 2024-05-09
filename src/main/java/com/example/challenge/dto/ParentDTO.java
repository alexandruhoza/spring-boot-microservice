package com.example.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentDTO implements Serializable {

    private Long id;
    private String name;
    private String description;
    private List<ChildDTO> children = new ArrayList<>();
    private Long version;
}
