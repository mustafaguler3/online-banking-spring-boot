package com.example.demo.service;

import com.example.demo.domain.Appointment;

import java.util.List;

public interface AppointmentService {
    Appointment createAppoinment(Appointment appointment);
    List<Appointment> findAll();
    Appointment findAppointmentById(int id);
    void confirmAppointment(int id);
}


















