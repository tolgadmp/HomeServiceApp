package com.finalproject.homeservice.payload;

import com.finalproject.homeservice.entity.Attribute;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class JobDefinitionDto {

    private Long id;

    private String name;

    private String description;

    private List<AttributeDto> attributes = new ArrayList<>();



}
