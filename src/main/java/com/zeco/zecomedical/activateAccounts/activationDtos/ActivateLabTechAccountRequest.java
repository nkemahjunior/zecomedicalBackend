package com.zeco.zecomedical.activateAccounts.activationDtos;

import com.zeco.zecomedical.model.Laboratories;
import com.zeco.zecomedical.model.Users;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class ActivateLabTechAccountRequest {


    //private Long id;
    //private Users userID;
    private Long labDepartment;
}
