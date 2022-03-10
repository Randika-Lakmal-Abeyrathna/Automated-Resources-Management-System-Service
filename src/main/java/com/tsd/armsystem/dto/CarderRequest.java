package com.tsd.armsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CarderRequest {

    private int limitation;
    private int current;
    private int schoolId;
    private int subjectId;
}
