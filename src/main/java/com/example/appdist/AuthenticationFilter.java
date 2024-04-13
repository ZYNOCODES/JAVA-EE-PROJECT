package com.example.appdist;

import java.io.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String loginURI = httpRequest.getContextPath() + "/Login";
        String signupURI = httpRequest.getContextPath() + "/SignUp";

        boolean loggedIn = session != null && session.getAttribute("token") != null;
        boolean loginRequest = httpRequest.getRequestURI().equals(loginURI);
        boolean signupRequest = httpRequest.getRequestURI().equals(signupURI);
        boolean resourceRequest = httpRequest.getRequestURI().startsWith(httpRequest.getContextPath() + "/resources/");

        if (loggedIn || loginRequest || signupRequest || resourceRequest) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(loginURI);
        }
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void destroy() {
    }

}