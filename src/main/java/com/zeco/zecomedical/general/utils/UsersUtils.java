package com.zeco.zecomedical.general.utils;

import com.zeco.zecomedical.dto.UsersRequestDto;
import com.zeco.zecomedical.dto.UsersResponseDto;
import com.zeco.zecomedical.model.Users;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

public class UsersUtils {

    public static UsersRequestDto entityToRequestDto(Users user){

        UsersRequestDto usersRequestDto = new UsersRequestDto();
        BeanUtils.copyProperties(user,usersRequestDto);

        return  usersRequestDto;
    }

    public static UsersResponseDto entityToResponseDto(Optional<Users> user){

        if(user.isEmpty()) throw new RuntimeException("can not convert users to response dto");

        UsersResponseDto usersResponseDto = new UsersResponseDto();
        BeanUtils.copyProperties(user,usersResponseDto);

        return  usersResponseDto;
    }




}
