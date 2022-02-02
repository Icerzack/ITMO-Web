package com.neevin.lab4.DTO;

import lombok.Data;
import lombok.NonNull;

@Data
public class PointDTO {
    @NonNull
    private double x;

    @NonNull
    private double y;

    @NonNull
    private double r;
}
