package com.zeco.zecomedical.patientBookAppointments.dtos;

import com.zeco.zecomedical.model.Doctors;
import lombok.Builder;
import lombok.Data;

import java.util.Calendar;

@Data
@Builder
public class AvailableAppointmentsResponse {

    private Long id;

    private Doctors doctor_id;

    private String day;

    private Calendar time_from;

    private Calendar time_to;
}
