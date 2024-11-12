package com.zeco.zecomedical.patientBookAppointments.controller;


import com.zeco.zecomedical.doctorsPostAppointment.dtos.MyAppointmentsResponse;
import com.zeco.zecomedical.general.projections.patient.doctorsAvailable.DoctorsAvailableProjection;
import com.zeco.zecomedical.patientBookAppointments.dtos.AppointmentRequestRequest;
import com.zeco.zecomedical.patientBookAppointments.dtos.PatientInfoResponse;
import com.zeco.zecomedical.patientBookAppointments.service.AppointmentsHistory;
import com.zeco.zecomedical.patientBookAppointments.service.BookAppointmentsService;
import com.zeco.zecomedical.dto.RequestResponse;
import com.zeco.zecomedical.general.repositories.DoctorsAvailableRepository;
import com.zeco.zecomedical.model.DoctorsAvailableForAppointment;
import com.zeco.zecomedical.patientBookAppointments.service.PatientInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class BookAppointmentController {

    private final DoctorsAvailableRepository doctorsAvailableRepository;
    private final BookAppointmentsService bookAppointmentsService;
    private final AppointmentsHistory appointmentsHistory;
    private final PatientInfoService patientInfoService;


    @GetMapping("/info")
    public ResponseEntity<PatientInfoResponse> getPatientInfoEndpoint(){
        return  ResponseEntity.ok(patientInfoService.getPatientInfo());
    }


    @GetMapping("/available_doctors/{speciality}")
    public ResponseEntity< List<DoctorsAvailableForAppointment> > getDoctorsAvailableForAppoinmentBySpeciality(@PathVariable(name = "speciality") String speciality){
        return ResponseEntity.ok(bookAppointmentsService.getAllDoctorsAvailableForAppointmentFilterBySpeciality(speciality));
    }

/***
 * A Page<T> instance, in addition to having the list of DoctorsAvailableForAppointment, also knows about the total number of available pages. It triggers an additional count query to achieve it. To avoid such an overhead cost, we can instead return a Slice<T> or a List<T>.
 * ***/
    @GetMapping("/available_doctors")
    public ResponseEntity< Page<DoctorsAvailableProjection>> getAllDoctorsAvailableForAppointment(@RequestParam(name="page", required = true) Integer page, @RequestParam(name = "size",required = true) Integer size){

        //http://localhost:8080/patient/available_doctors?page=0&size=2
        //return ResponseEntity.ok( doctorsAvailableRepository.findAll(PageRequest.of(page,size)));
        return ResponseEntity.ok( doctorsAvailableRepository.findByTimeFromGreaterThan(LocalDateTime.now(),PageRequest.of(page,size) ));

    }


    @PostMapping("/book_appointment")
    public ResponseEntity<RequestResponse> bookAppointment(@RequestBody AppointmentRequestRequest request){
        return ResponseEntity.ok(bookAppointmentsService.bookAppointment(request));
    }

    @GetMapping("/appointments")
    public ResponseEntity<List<MyAppointmentsResponse>> allPatientAppointmentHistoryEndpoint(){

        return ResponseEntity.ok( appointmentsHistory.allPatientAppointmentHistory());

    }



    @GetMapping("/appointments/{status}")
    public ResponseEntity<List<MyAppointmentsResponse>> allPatientUpcomingAppointmentsEndpoint(@PathVariable String status){

        return ResponseEntity.ok( appointmentsHistory.allPatientUpcomingAppointments(status));

    }



}
