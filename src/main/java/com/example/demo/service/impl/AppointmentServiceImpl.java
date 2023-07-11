package com.example.demo.service.impl;

import com.example.demo.domain.Appointment;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.service.AppoinmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AppointmentServiceImpl implements AppoinmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Appointment createAppoinment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment findAppointmentById(int id) {
        return appointmentRepository.findAppointmentById(id);
    }

    @Override
    public void confirmAppointment(int id) {
        Appointment appointment = findAppointmentById(id);
        appointment.setConfirmed(true);
        appointmentRepository.save(appointment);
    }
}
