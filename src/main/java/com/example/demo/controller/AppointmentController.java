package com.example.demo.controller;

import com.example.demo.domain.Appointment;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private UserService userService;

    @RequestMapping("/create")
    public String createAppointment(Model model){
        Appointment appointment = new Appointment();
        model.addAttribute("appointment",appointment);
        model.addAttribute("dateString","");

        return "appointment";
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String createAppointmentPost(@ModelAttribute("appointment") Appointment appointment,
                                        @ModelAttribute("dateString") String date,
                                        Model model) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        Date date1 = format.parse(date);
        appointment.setDate(date1);
        appointmentService.createAppoinment(appointment);

        return "appointment";
    }
}




















