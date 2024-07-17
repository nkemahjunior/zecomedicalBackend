package com.zeco.zecomedical.auth.config;

import com.zeco.zecomedical.general.utils.MyDebug;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;


@Log4j2
@Component
public class LoggerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {

        MyDebug.printBlock();
        MyDebug.printBlock();
        log.info("PRE HANDLE PRE HANDLE PRE HANDLE");

        log.info("[preHandle][" + request + "]" + "[" + request.getMethod()
                + "]" + request.getRequestURI());


        if (request.getCookies() != null) {
            log.info(" LOGGING THE COOKIES");

            Arrays.stream(request.getCookies()).forEach(cookie -> {
                /*log.info(cookie.getName());
                log.info(cookie.getAttributes());
                log.info(cookie.getValue());
                log.info(cookie.getDomain());*/
            });
            log.info("FINISHED LOGGING THE COOKIES");

        } else {
            log.info("No cookies found");
        }


        log.info("END OF PRE HANDLE, END OF PRE HANDLE , END OF PRE HANDLE");
        MyDebug.printBlock();
        MyDebug.printBlock();



        return true;
    }

}
