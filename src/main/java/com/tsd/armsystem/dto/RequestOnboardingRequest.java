package com.tsd.armsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestOnboardingRequest {

    private String date;
    private int requestId;
    private int carderId;

}
