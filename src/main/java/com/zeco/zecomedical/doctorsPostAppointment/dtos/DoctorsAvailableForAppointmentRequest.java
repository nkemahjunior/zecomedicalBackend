package com.zeco.zecomedical.doctorsPostAppointment.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorsAvailableForAppointmentRequest {

    private String day;

    private int startYear;

    private int startMonth;

    private  int startDayNumber;

    private int endYear;

    private int endMonth;

    private  int endDayNumber;

    private int time_from_hour;
    private  int time_from_min;

    private int time_to_hour;
    private int time_to_min;
}
