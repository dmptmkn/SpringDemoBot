package com.example.springdemobot.repository;

import com.example.springdemobot.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepository extends JpaRepository<Ad, Long> {
}