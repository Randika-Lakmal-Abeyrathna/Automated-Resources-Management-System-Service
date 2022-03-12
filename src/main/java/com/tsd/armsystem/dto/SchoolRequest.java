package com.tsd.armsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SchoolRequest {

    private String name;
    private int cityId;
    private int zonalId;
    private int schoolTypeId;
}
