package com.example.registrationlogindemo.repository;

import com.example.registrationlogindemo.entity.MonTran;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitorRepository extends JpaRepository<MonTran, Long> {
}
