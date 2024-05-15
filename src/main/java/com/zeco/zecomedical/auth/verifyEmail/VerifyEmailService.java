package com.zeco.zecomedical.auth.verifyEmail;


import com.zeco.zecomedical.customExceptions.MyException;
import com.zeco.zecomedical.dto.RequestResponse;
import com.zeco.zecomedical.general.repositories.UserRolesRepository;
import com.zeco.zecomedical.general.repositories.UsersRepository;
import com.zeco.zecomedical.general.repositories.VerificationTokenRepository;
import com.zeco.zecomedical.general.utils.MyDebug;
import com.zeco.zecomedical.model.Roles;
import com.zeco.zecomedical.model.Users;
import com.zeco.zecomedical.model.VerificationToken;
import com.zeco.zecomedical.notification.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Log4j2
public class VerifyEmailService {

    private final VerificationTokenRepository tokenRepository;
    private final EmailService emailService;
    private final UsersRepository usersRepository;
    private final UserRolesRepository userRolesRepository;


    //@Async
    public void saveAndSendEmailVerificationToken(Users username) {

        UUID token = UUID.randomUUID();
        LocalDateTime expiry = LocalDateTime.now().plusHours(5);

        VerificationToken verificationToken = VerificationToken.builder()
                .username(username)
                .token(token)
                .expiryTime(expiry)
                .build();

        tokenRepository.save(verificationToken);

        Optional<Users> user = usersRepository.findByUsername(username.getUsername());
        if (user.isEmpty()) throw new MyException(HttpStatus.NOT_FOUND.value(), "user not found");

        //async method
       emailService.verifyEmailAddress(token,user.get().getEmail());


    }


    public RequestResponse validateEmailToken(UUID token){
        Optional<VerificationToken> tokenOptional = tokenRepository.findByToken(token);
        if (tokenOptional.isEmpty()) throw new MyException(HttpStatus.NOT_FOUND.value(), "token not found");

        Optional<Users> user = usersRepository.findByUsername(tokenOptional.get().getUsername().getUsername());
        if (user.isEmpty()) throw new MyException(HttpStatus.NOT_FOUND.value(), "no user found with that token");

        Roles role = new Roles();
        role.setId(5);

        Users user1 = user.get();
        user1.setRole(role);
        user1.setVerified(true);

        usersRepository.save(user1);

        return RequestResponse.builder()
                        .status(HttpStatus.OK.value())
                        .message("user successfully verified")
                        .build();

    }



    public RequestResponse resendVerificationEmail(String email){

       Optional<Users> user =  usersRepository.findByEmail(email);


       if(user.isEmpty()) {
           return RequestResponse.builder()
                   .status(HttpStatus.NOT_FOUND.value())
                   .message("email not found")
                   .build();
       }

       //4 is the role of unverified users
       if(user.get().getRole().getRoles().equals("UNVERIFIED")){

           saveAndSendEmailVerificationToken(user.get());

           return RequestResponse.builder()
                   .status(HttpStatus.OK.value())
                   .message("email sent, proceed and verify email")
                   .build();

       }




        return RequestResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("error happened")
                .build();

    }
}

