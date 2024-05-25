package com.zeco.zecomedical.doctorsPostAppointment.controller;


import com.zeco.zecomedical.doctorsPostAppointment.dtos.DoctorsAvailableForAppointmentRequest;
import com.zeco.zecomedical.doctorsPostAppointment.dtos.MyAppointmentsResponse;
import com.zeco.zecomedical.doctorsPostAppointment.service.GetAndChangeAppointmentDetailsService;
import com.zeco.zecomedical.doctorsPostAppointment.service.PostAppointmentService;
import com.zeco.zecomedical.dto.RequestResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorsPostAppointmentController {

    private final PostAppointmentService postAppointmentService;
    private final GetAndChangeAppointmentDetailsService getAndChangeAppointmentDetailsService;


    @PostMapping("/appointment")
    public ResponseEntity<RequestResponse> postAppointmentEndpoint(@RequestBody List<DoctorsAvailableForAppointmentRequest> data, HttpServletRequest req){

        return ResponseEntity.ok(postAppointmentService.PostAppointmentAsDoctor(req,data));
    }



    @GetMapping("/appointments") ///appointments?page=0&size=2
    public ResponseEntity<Page<MyAppointmentsResponse>> getAllMyAppointments(@RequestParam(name = "page")Integer page, @RequestParam("size") Integer size ){

        return ResponseEntity.ok(getAndChangeAppointmentDetailsService.getUpcomingAppointmentRequestsForADoctor(page,size));
    }



    @GetMapping("/appointments/accepted")///accepted?page=0&size=2
    public ResponseEntity<Page<MyAppointmentsResponse>> getAcceptedUpcomingAppointments(@RequestParam("page") Integer page, @RequestParam("size") Integer size){

        return ResponseEntity.ok(getAndChangeAppointmentDetailsService.getAcceptedUpcomingAppointments(page,size));
    }



    @GetMapping("/appointments/accepted/{patientName}")
    public ResponseEntity<List<MyAppointmentsResponse>> getAcceptedUpcomingAppointmentsFilterByPatientName(@PathVariable String patientName){

        return ResponseEntity.ok(getAndChangeAppointmentDetailsService.getAcceptedUpcomingAppointmentsFilterByPatientName(patientName));
    }




    @PutMapping("/appointment/status/{id}/{status}")///doctor/appointment/status/14/DECLINED
    public ResponseEntity<RequestResponse> updateAppointmentStatusEndpoint(@PathVariable Long id, @PathVariable String status) {
        return ResponseEntity.ok(getAndChangeAppointmentDetailsService.updateAppointmentStatus(id,status));
    }



    /*@PutMapping("/appointment/diagnosis/{id}")
    public ResponseEntity<RequestResponse> updatePatientDiagnosis(@PathVariable Long id, @RequestBody DoctorDiagnosisRequest doctorDiagnosisRequest){
        return ResponseEntity.ok(getAndChangeAppointmentDetailsService.treatmentProcess(id,doctorDiagnosisRequest));
    }*/
}
