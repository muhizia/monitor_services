package com.example.registrationlogindemo.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonitorDto {
    private String serviceID;
    private String serviceName;
    private int noTra;
    private LocalDateTime lastAt;
}
