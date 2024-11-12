package com.zeco.zecomedical.laboratory.dto;


import lombok.Data;

@Data
public class TestResults {

    private Long id;
    private Boolean result;
    private  Boolean completed;
    private String notes;
}
