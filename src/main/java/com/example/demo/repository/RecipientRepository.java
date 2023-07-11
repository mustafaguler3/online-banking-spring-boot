package com.example.demo.repository;

import com.example.demo.domain.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipientRepository extends JpaRepository<Recipient,Long> {
    Recipient findByName(String name);
    List<Recipient> findAll();
    Recipient deleteByName(String recipientName);
}
