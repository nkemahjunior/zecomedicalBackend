package com.zeco.zecomedical.patientAndDoctors.controller;

import com.zeco.zecomedical.model.AppointmentRequests;
import com.zeco.zecomedical.patientAndDoctors.service.DoctorPatientsSharedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class DoctorPatientsSharedController {

    private final DoctorPatientsSharedService doctorPatientsSharedService;

    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentRequests>> getAppointmentsHistory(){
        return ResponseEntity.ok(doctorPatientsSharedService.getPastAndPresentAppointments());
    }
}
