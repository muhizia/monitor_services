package com.example.registrationlogindemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "MonTran")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonTran {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ConID")
    private int conID;
    @Column(name = "ServiceName")
    private String serviceName;
    @Column(name = "ServiceID")
    private String serviceID;
    @Column(name = "Description")
    private String description;
    @Column(name = "Query")
    private String query;
    @Column(name = "NoTransaction")
    private int noTra;
    @Column(name = "LastTransaction")
    private LocalDateTime lastAt;
    @Column(name = "CheckTime")
    private LocalDateTime checkAt;
    @Column(name = "Category")
    private char category;
    @Column(name = "Active")
    private boolean active;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
