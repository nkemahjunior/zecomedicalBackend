package com.zeco.zecomedical.auth.service;

import com.zeco.zecomedical.auth.AuthDtos.SigninRequestDto;
import com.zeco.zecomedical.auth.AuthDtos.SignoutResponseDto;
import com.zeco.zecomedical.auth.AuthDtos.SignupResponseDto;
import com.zeco.zecomedical.dto.UsersRequestDto;
import com.zeco.zecomedical.dto.UsersResponseDto;
import com.zeco.zecomedical.model.Users;
import com.zeco.zecomedical.general.repositories.UsersRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;


import java.util.Calendar;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Log4j2
public class AuthenticationService {

    private  final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;


    private final AuthenticationManager authenticationManager;
    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    public SignupResponseDto signup(UsersRequestDto signupData){

        if(usersRepository.findByUsername(signupData.getUsername()).isPresent() || usersRepository.findByEmail(signupData.getEmail()).isPresent()){

            return new SignupResponseDto(HttpStatus.CONFLICT.value(), "Account already exist");
        }


        Calendar calendar = Calendar.getInstance();
        calendar.set(signupData.getYear(),signupData.getMonth(),signupData.getDay());

        Users user = Users.builder()
                .name(signupData.getName())
                .username(signupData.getUsername())
                .gender(signupData.getGender())
                .dob(calendar)
                .address(signupData.getAddress())
                .email(signupData.getEmail())
                .password(passwordEncoder.encode(signupData.getPassword()))
                .role(signupData.getRole())
                .isAuthenticated(false)
                .build();

        usersRepository.save(user);

        return new SignupResponseDto(HttpStatus.CREATED.value(), "Account created successfully, proceed and login");

    }


    public UsersResponseDto signin(SigninRequestDto signinData, HttpServletRequest request, HttpServletResponse response) {


            Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(signinData.getUsername(), signinData.getPassword());
            Authentication authenticationResponse = authenticationManager.authenticate(authenticationRequest);

            SecurityContext context = securityContextHolderStrategy.createEmptyContext();
            context.setAuthentication(authenticationResponse);
            this.securityContextHolderStrategy.setContext(context);
            securityContextRepository.saveContext(context, request, response);

             return checkSession(request);

             //i am handling the BadCredentials exception in the controllerAdvice class encase  authenticationManager.authenticate throws it due to wrong login details

    }


    public SignoutResponseDto signout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {


        HttpSession session = request.getSession(false);
        if(session == null) throw new RuntimeException();

        String username = request.getUserPrincipal().getName();
        Optional<Users> user = usersRepository.findByUsername(username);

        if(user.isEmpty())  throw new RuntimeException();

        Users user1 = user.get();
        user1.setIsAuthenticated(false);
        usersRepository.save(user1);

        logoutHandler.logout(request, response, authentication);
        return new SignoutResponseDto("successfully signed out");



    }


    public UsersResponseDto checkSession(HttpServletRequest request){

        HttpSession session = request.getSession(false);//return null instead of creating a new session if there is no available session for the user


        if( session != null){

           String username = request.getUserPrincipal().getName();

           Optional<Users> userData = usersRepository.findByUsername(username);

           if(userData.isEmpty()) throw new RuntimeException("from checkSession method");

           Users user = userData.get();
           user.setIsAuthenticated(true);
            Users user1 = usersRepository.save(user);

            return UsersResponseDto.builder()
                    .name(user1.getName())
                    .username(user1.getUsername())
                    .gender(user1.getGender())
                    .dob(user1.getDob())
                    .address(user1.getAddress())
                    .email(user1.getEmail())
                    .role(user1.getRole())
                    .isAuthenticated(true)
                    .build();


        }else{
            return  UsersResponseDto.builder()
                    .errorMessage("session expired, login again")
                    .isAuthenticated(false)
                    .build();
        }

    }
}
