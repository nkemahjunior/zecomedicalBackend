package com.zeco.zecomedical.auth.cors;

import com.zeco.zecomedical.general.utils.MyDebug;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class CsrfCookieFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");
        MyDebug.printBlock();
        System.out.println(csrfToken.getToken());
        System.out.println(csrfToken.getHeaderName());
        System.out.println(csrfToken.getParameterName());
        MyDebug.printBlock();
        // Render the token value to a cookie by causing the deferred token to be loaded
        csrfToken.getToken();



        filterChain.doFilter(request, response);
    }
}
