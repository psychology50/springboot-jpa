package com.example.jpa.domain.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.LocalDateTime;

@Embeddable
public class Period {
    @Temporal(TemporalType.DATE)
    LocalDateTime startDate;

    @Temporal(TemporalType.DATE)
    LocalDateTime endDate;
}
