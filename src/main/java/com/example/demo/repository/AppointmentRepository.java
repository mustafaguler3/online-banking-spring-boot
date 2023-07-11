package com.example.demo.repository;

import com.example.demo.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    Appointment findAppointmentById(int id);
}
