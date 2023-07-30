package com.tsd.armsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UpdateRequest {

    private int id;
    private int schoolId;
    private int provinceId;
    private String comment;
}
