package com.zeco.zecomedical.general.projections.lab;



import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ConsultationTable {

    Long getId();
    String getDiagnosisNotes();
    LocalDateTime getTimestamp();
    Boolean getComeForCheckup();
    LocalDateTime getCheckupDate();
    UUID getLabResultsBloodBank();
    UUID getLabResultsImmunology();
    UUID getLabResultsMicrobiology();
    UUID getLabResultsParasitology();
    Boolean getSessionFinished();
    String getMedicinePrescribed();
}


/**
 *     {
 *         "id": 67,
 *         "doctorID": {
 *             "doctor_id": 7,
 *             "uuid": {
 *                 "id": "7cec34a2-774d-4539-a158-05da2f7442ac",
 *                 "name": "zecoD1",
 *                 "username": "zecoD1",
 *                 "gender": "M",
 *                 "dob": "2012-09-09T23:00:00.000+00:00",
 *                 "address": "nicagaura",
 *                 "email": "nkemahjunior679205967@gmail.com1",
 *                 "password": "$2a$10$C/aD628Q/Flx2oUgV5TMLeisQlZQzPaF.pOciUXmpb7tMIr3/765G",
 *                 "role": {
 *                     "id": 2,
 *                     "roles": "DOCTOR"
 *                 },
 *                 "isAuthenticated": true,
 *                 "verified": false,
 *                 "profilePhotoUrl": "https://img.freepik.com/free-photo/smiling-doctor-with-strethoscope-isolated-grey_651396-974.jpg?w=1380&t=st=1716311024~exp=1716311624~hmac=353dd89aaf36e503313e551b02c6ec867918722cfcc092eaa24da54777c9ca02"
 *             },
 *             "speciality": "dentist",
 *             "email": null
 *         },
 *         "patientID": {
 *             "id": 19,
 *             "weight": null,
 *             "bloodGroup": null,
 *             "bloodPressure": null,
 *             "patientID": {
 *                 "id": "cc642390-6efd-48bd-a4a4-011f4b9b5a22",
 *                 "name": "Nkemah_Junior",
 *                 "username": "zeco",
 *                 "gender": "male",
 *                 "dob": "2004-06-14T23:00:00.000+00:00",
 *                 "address": "Bamenda",
 *                 "email": "nkemahjunior679205967@gmail.com",
 *                 "password": "$2a$10$WXhYVTQl4NpBlIAI3vyFCemg/2fiystzgzqcu2a84eGsbYEVFipjO",
 *                 "role": {
 *                     "id": 3,
 *                     "roles": "PATIENT"
 *                 },
 *                 "isAuthenticated": true,
 *                 "verified": false,
 *                 "profilePhotoUrl": "https://res.cloudinary.com/dnx4qdfwb/image/upload/v1716808329/userProfiles/f23bpof6ydtfusoa3cc4.jpg"
 *             },
 *             "email": null
 *         },
 *         "diagnosisNotes": "hueiop^$",
 *         "timestamp": "2024-05-31T09:57:50.143721",
 *         "comeForCheckup": true,
 *         "checkupDate": "2024-06-02T15:45:00",
 *         "labResultsBloodBank": [
 *             {
 *                 "id": 165,
 *                 "labResultsBloodBank": {
 *                     "id": 67,
 *                     "doctorID": {
 *                         "doctor_id": 7,
 *                         "uuid": {
 *                             "id": "7cec34a2-774d-4539-a158-05da2f7442ac",
 *                             "name": "zecoD1",
 *                             "username": "zecoD1",
 *                             "gender": "M",
 *                             "dob": "2012-09-09T23:00:00.000+00:00",
 *                             "address": "nicagaura",
 *                             "email": "nkemahjunior679205967@gmail.com1",
 *                             "password": "$2a$10$C/aD628Q/Flx2oUgV5TMLeisQlZQzPaF.pOciUXmpb7tMIr3/765G",
 *                             "role": {
 *                                 "id": 2,
 *                                 "roles": "DOCTOR"
 *                             },
 *                             "isAuthenticated": true,
 *                             "verified": false,
 *                             "profilePhotoUrl": "https://img.freepik.com/free-photo/smiling-doctor-with-strethoscope-isolated-grey_651396-974.jpg?w=1380&t=st=1716311024~exp=1716311624~hmac=353dd89aaf36e503313e551b02c6ec867918722cfcc092eaa24da54777c9ca02"
 *                         },
 *                         "speciality": "dentist",
 *                         "email": null
 *                     },
 *                     "patientID": {
 *                         "id": 19,
 *                         "weight": null,
 *                         "bloodGroup": null,
 *                         "bloodPressure": null,
 *                         "patientID": {
 *                             "id": "cc642390-6efd-48bd-a4a4-011f4b9b5a22",
 *                             "name": "Nkemah_Junior",
 *                             "username": "zeco",
 *                             "gender": "male",
 *                             "dob": "2004-06-14T23:00:00.000+00:00",
 *                             "address": "Bamenda",
 *                             "email": "nkemahjunior679205967@gmail.com",
 *                             "password": "$2a$10$WXhYVTQl4NpBlIAI3vyFCemg/2fiystzgzqcu2a84eGsbYEVFipjO",
 *                             "role": {
 *                                 "id": 3,
 *                                 "roles": "PATIENT"
 *                             },
 *                             "isAuthenticated": true,
 *                             "verified": false,
 *                             "profilePhotoUrl": "https://res.cloudinary.com/dnx4qdfwb/image/upload/v1716808329/userProfiles/f23bpof6ydtfusoa3cc4.jpg"
 *                         },
 *                         "email": null
 *                     },
 *                     "diagnosisNotes": "hueiop^$",
 *                     "timestamp": "2024-05-31T09:57:50.143721",
 *                     "comeForCheckup": true,
 *                     "checkupDate": "2024-06-02T15:45:00",
 *                     "labResultsBloodBank": "461b526a-fbef-48c1-8cd4-bc9d817e1197",
 *                     "labResultsImmunology": null,
 *                     "labResultsMicrobiology": null,
 *                     "labResultsParasitology": null,
 *                     "sessionFinished": true,
 *                     "medicinePrescribed": "",
 *                     "status": "FINISHED"
 *                 },
 *                 "doctorID": {
 *                     "doctor_id": 7,
 *                     "uuid": {
 *                         "id": "7cec34a2-774d-4539-a158-05da2f7442ac",
 *                         "name": "zecoD1",
 *                         "username": "zecoD1",
 *                         "gender": "M",
 *                         "dob": "2012-09-09T23:00:00.000+00:00",
 *                         "address": "nicagaura",
 *                         "email": "nkemahjunior679205967@gmail.com1",
 *                         "password": "$2a$10$C/aD628Q/Flx2oUgV5TMLeisQlZQzPaF.pOciUXmpb7tMIr3/765G",
 *                         "role": {
 *                             "id": 2,
 *                             "roles": "DOCTOR"
 *                         },
 *                         "isAuthenticated": true,
 *                         "verified": false,
 *                         "profilePhotoUrl": "https://img.freepik.com/free-photo/smiling-doctor-with-strethoscope-isolated-grey_651396-974.jpg?w=1380&t=st=1716311024~exp=1716311624~hmac=353dd89aaf36e503313e551b02c6ec867918722cfcc092eaa24da54777c9ca02"
 *                     },
 *                     "speciality": "dentist",
 *                     "email": null
 *                 },
 *                 "labDepartment": {
 *                     "id": 4,
 *                     "name": "Blood Bank"
 *                 },
 *                 "patientName": "Nkemah_Junior",
 *                 "patientID": {
 *                     "id": 19,
 *                     "weight": null,
 *                     "bloodGroup": null,
 *                     "bloodPressure": null,
 *                     "patientID": {
 *                         "id": "cc642390-6efd-48bd-a4a4-011f4b9b5a22",
 *                         "name": "Nkemah_Junior",
 *                         "username": "zeco",
 *                         "gender": "male",
 *                         "dob": "2004-06-14T23:00:00.000+00:00",
 *                         "address": "Bamenda",
 *                         "email": "nkemahjunior679205967@gmail.com",
 *                         "password": "$2a$10$WXhYVTQl4NpBlIAI3vyFCemg/2fiystzgzqcu2a84eGsbYEVFipjO",
 *                         "role": {
 *                             "id": 3,
 *                             "roles": "PATIENT"
 *                         },
 *                         "isAuthenticated": true,
 *                         "verified": false,
 *                         "profilePhotoUrl": "https://res.cloudinary.com/dnx4qdfwb/image/upload/v1716808329/userProfiles/f23bpof6ydtfusoa3cc4.jpg"
 *                     },
 *                     "email": null
 *                 },
 *                 "labTestRequest": "ertgre",
 *                 "result": true,
 *                 "completed": true,
 *                 "creationTimestamp": "2024-05-31T09:58:09.373475",
 *                 "notes": null
 *             },
 *             {
 *                 "id": 166,
 *                 "labResultsBloodBank": {
 *                     "id": 67,
 *                     "doctorID": {
 *                         "doctor_id": 7,
 *                         "uuid": {
 *                             "id": "7cec34a2-774d-4539-a158-05da2f7442ac",
 *                             "name": "zecoD1",
 *                             "username": "zecoD1",
 *                             "gender": "M",
 *                             "dob": "2012-09-09T23:00:00.000+00:00",
 *                             "address": "nicagaura",
 *                             "email": "nkemahjunior679205967@gmail.com1",
 *                             "password": "$2a$10$C/aD628Q/Flx2oUgV5TMLeisQlZQzPaF.pOciUXmpb7tMIr3/765G",
 *                             "role": {
 *                                 "id": 2,
 *                                 "roles": "DOCTOR"
 *                             },
 *                             "isAuthenticated": true,
 *                             "verified": false,
 *                             "profilePhotoUrl": "https://img.freepik.com/free-photo/smiling-doctor-with-strethoscope-isolated-grey_651396-974.jpg?w=1380&t=st=1716311024~exp=1716311624~hmac=353dd89aaf36e503313e551b02c6ec867918722cfcc092eaa24da54777c9ca02"
 *                         },
 *                         "speciality": "dentist",
 *                         "email": null
 *                     },
 *                     "patientID": {
 *                         "id": 19,
 *                         "weight": null,
 *                         "bloodGroup": null,
 *                         "bloodPressure": null,
 *                         "patientID": {
 *                             "id": "cc642390-6efd-48bd-a4a4-011f4b9b5a22",
 *                             "name": "Nkemah_Junior",
 *                             "username": "zeco",
 *                             "gender": "male",
 *                             "dob": "2004-06-14T23:00:00.000+00:00",
 *                             "address": "Bamenda",
 *                             "email": "nkemahjunior679205967@gmail.com",
 *                             "password": "$2a$10$WXhYVTQl4NpBlIAI3vyFCemg/2fiystzgzqcu2a84eGsbYEVFipjO",
 *                             "role": {
 *                                 "id": 3,
 *                                 "roles": "PATIENT"
 *                             },
 *                             "isAuthenticated": true,
 *                             "verified": false,
 *                             "profilePhotoUrl": "https://res.cloudinary.com/dnx4qdfwb/image/upload/v1716808329/userProfiles/f23bpof6ydtfusoa3cc4.jpg"
 *                         },
 *                         "email": null
 *                     },
 *                     "diagnosisNotes": "hueiop^$",
 *                     "timestamp": "2024-05-31T09:57:50.143721",
 *                     "comeForCheckup": true,
 *                     "checkupDate": "2024-06-02T15:45:00",
 *                     "labResultsBloodBank": "461b526a-fbef-48c1-8cd4-bc9d817e1197",
 *                     "labResultsImmunology": null,
 *                     "labResultsMicrobiology": null,
 *                     "labResultsParasitology": null,
 *                     "sessionFinished": true,
 *                     "medicinePrescribed": "",
 *                     "status": "FINISHED"
 *                 },
 *                 "doctorID": {
 *                     "doctor_id": 7,
 *                     "uuid": {
 *                         "id": "7cec34a2-774d-4539-a158-05da2f7442ac",
 *                         "name": "zecoD1",
 *                         "username": "zecoD1",
 *                         "gender": "M",
 *                         "dob": "2012-09-09T23:00:00.000+00:00",
 *                         "address": "nicagaura",
 *                         "email": "nkemahjunior679205967@gmail.com1",
 *                         "password": "$2a$10$C/aD628Q/Flx2oUgV5TMLeisQlZQzPaF.pOciUXmpb7tMIr3/765G",
 *                         "role": {
 *                             "id": 2,
 *                             "roles": "DOCTOR"
 *                         },
 *                         "isAuthenticated": true,
 *                         "verified": false,
 *                         "profilePhotoUrl": "https://img.freepik.com/free-photo/smiling-doctor-with-strethoscope-isolated-grey_651396-974.jpg?w=1380&t=st=1716311024~exp=1716311624~hmac=353dd89aaf36e503313e551b02c6ec867918722cfcc092eaa24da54777c9ca02"
 *                     },
 *                     "speciality": "dentist",
 *                     "email": null
 *                 },
 *                 "labDepartment": {
 *                     "id": 4,
 *                     "name": "Blood Bank"
 *                 },
 *                 "patientName": "Nkemah_Junior",
 *                 "patientID": {
 *                     "id": 19,
 *                     "weight": null,
 *                     "bloodGroup": null,
 *                     "bloodPressure": null,
 *                     "patientID": {
 *                         "id": "cc642390-6efd-48bd-a4a4-011f4b9b5a22",
 *                         "name": "Nkemah_Junior",
 *                         "username": "zeco",
 *                         "gender": "male",
 *                         "dob": "2004-06-14T23:00:00.000+00:00",
 *                         "address": "Bamenda",
 *                         "email": "nkemahjunior679205967@gmail.com",
 *                         "password": "$2a$10$WXhYVTQl4NpBlIAI3vyFCemg/2fiystzgzqcu2a84eGsbYEVFipjO",
 *                         "role": {
 *                             "id": 3,
 *                             "roles": "PATIENT"
 *                         },
 *                         "isAuthenticated": true,
 *                         "verified": false,
 *                         "profilePhotoUrl": "https://res.cloudinary.com/dnx4qdfwb/image/upload/v1716808329/userProfiles/f23bpof6ydtfusoa3cc4.jpg"
 *                     },
 *                     "email": null
 *                 },
 *                 "labTestRequest": "zertghgrez",
 *                 "result": true,
 *                 "completed": true,
 *                 "creationTimestamp": "2024-05-31T09:58:09.373475",
 *                 "notes": null
 *             },
 *             {
 *                 "id": 167,
 *                 "labResultsBloodBank": {
 *                     "id": 67,
 *                     "doctorID": {
 *                         "doctor_id": 7,
 *                         "uuid": {
 *                             "id": "7cec34a2-774d-4539-a158-05da2f7442ac",
 *                             "name": "zecoD1",
 *                             "username": "zecoD1",
 *                             "gender": "M",
 *                             "dob": "2012-09-09T23:00:00.000+00:00",
 *                             "address": "nicagaura",
 *                             "email": "nkemahjunior679205967@gmail.com1",
 *                             "password": "$2a$10$C/aD628Q/Flx2oUgV5TMLeisQlZQzPaF.pOciUXmpb7tMIr3/765G",
 *                             "role": {
 *                                 "id": 2,
 *                                 "roles": "DOCTOR"
 *                             },
 *                             "isAuthenticated": true,
 *                             "verified": false,
 *                             "profilePhotoUrl": "https://img.freepik.com/free-photo/smiling-doctor-with-strethoscope-isolated-grey_651396-974.jpg?w=1380&t=st=1716311024~exp=1716311624~hmac=353dd89aaf36e503313e551b02c6ec867918722cfcc092eaa24da54777c9ca02"
 *                         },
 *                         "speciality": "dentist",
 *                         "email": null
 *                     },
 *                     "patientID": {
 *                         "id": 19,
 *                         "weight": null,
 *                         "bloodGroup": null,
 *                         "bloodPressure": null,
 *                         "patientID": {
 *                             "id": "cc642390-6efd-48bd-a4a4-011f4b9b5a22",
 *                             "name": "Nkemah_Junior",
 *                             "username": "zeco",
 *                             "gender": "male",
 *                             "dob": "2004-06-14T23:00:00.000+00:00",
 *                             "address": "Bamenda",
 *                             "email": "nkemahjunior679205967@gmail.com",
 *                             "password": "$2a$10$WXhYVTQl4NpBlIAI3vyFCemg/2fiystzgzqcu2a84eGsbYEVFipjO",
 *                             "role": {
 *                                 "id": 3,
 *                                 "roles": "PATIENT"
 *                             },
 *                             "isAuthenticated": true,
 *                             "verified": false,
 *                             "profilePhotoUrl": "https://res.cloudinary.com/dnx4qdfwb/image/upload/v1716808329/userProfiles/f23bpof6ydtfusoa3cc4.jpg"
 *                         },
 *                         "email": null
 *                     },
 *                     "diagnosisNotes": "hueiop^$",
 *                     "timestamp": "2024-05-31T09:57:50.143721",
 *                     "comeForCheckup": true,
 *                     "checkupDate": "2024-06-02T15:45:00",
 *                     "labResultsBloodBank": "461b526a-fbef-48c1-8cd4-bc9d817e1197",
 *                     "labResultsImmunology": null,
 *                     "labResultsMicrobiology": null,
 *                     "labResultsParasitology": null,
 *                     "sessionFinished": true,
 *                     "medicinePrescribed": "",
 *                     "status": "FINISHED"
 *                 },
 *                 "doctorID": {
 *                     "doctor_id": 7,
 *                     "uuid": {
 *                         "id": "7cec34a2-774d-4539-a158-05da2f7442ac",
 *                         "name": "zecoD1",
 *                         "username": "zecoD1",
 *                         "gender": "M",
 *                         "dob": "2012-09-09T23:00:00.000+00:00",
 *                         "address": "nicagaura",
 *                         "email": "nkemahjunior679205967@gmail.com1",
 *                         "password": "$2a$10$C/aD628Q/Flx2oUgV5TMLeisQlZQzPaF.pOciUXmpb7tMIr3/765G",
 *                         "role": {
 *                             "id": 2,
 *                             "roles": "DOCTOR"
 *                         },
 *                         "isAuthenticated": true,
 *                         "verified": false,
 *                         "profilePhotoUrl": "https://img.freepik.com/free-photo/smiling-doctor-with-strethoscope-isolated-grey_651396-974.jpg?w=1380&t=st=1716311024~exp=1716311624~hmac=353dd89aaf36e503313e551b02c6ec867918722cfcc092eaa24da54777c9ca02"
 *                     },
 *                     "speciality": "dentist",
 *                     "email": null
 *                 },
 *                 "labDepartment": {
 *                     "id": 4,
 *                     "name": "Blood Bank"
 *                 },
 *                 "patientName": "Nkemah_Junior",
 *                 "patientID": {
 *                     "id": 19,
 *                     "weight": null,
 *                     "bloodGroup": null,
 *                     "bloodPressure": null,
 *                     "patientID": {
 *                         "id": "cc642390-6efd-48bd-a4a4-011f4b9b5a22",
 *                         "name": "Nkemah_Junior",
 *                         "username": "zeco",
 *                         "gender": "male",
 *                         "dob": "2004-06-14T23:00:00.000+00:00",
 *                         "address": "Bamenda",
 *                         "email": "nkemahjunior679205967@gmail.com",
 *                         "password": "$2a$10$WXhYVTQl4NpBlIAI3vyFCemg/2fiystzgzqcu2a84eGsbYEVFipjO",
 *                         "role": {
 *                             "id": 3,
 *                             "roles": "PATIENT"
 *                         },
 *                         "isAuthenticated": true,
 *                         "verified": false,
 *                         "profilePhotoUrl": "https://res.cloudinary.com/dnx4qdfwb/image/upload/v1716808329/userProfiles/f23bpof6ydtfusoa3cc4.jpg"
 *                     },
 *                     "email": null
 *                 },
 *                 "labTestRequest": "a\"rtytr'\"é",
 *                 "result": true,
 *                 "completed": true,
 *                 "creationTimestamp": "2024-05-31T09:58:09.373475",
 *                 "notes": null
 *             }
 *         ],
 *         "labResultsImmunology": null,
 *         "labResultsMicrobiology": null,
 *         "labResultsParasitology": null,
 *         "sessionFinished": true,
 *         "medicinePrescribed": ""
 *     },**/