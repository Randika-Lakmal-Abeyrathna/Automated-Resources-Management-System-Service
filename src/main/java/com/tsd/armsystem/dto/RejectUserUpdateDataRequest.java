package com.tsd.armsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RejectUserUpdateDataRequest {

    private String reason;
    private int id;
}
