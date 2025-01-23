package com.crio.video_rental_system_advanced.config;

import com.crio.video_rental_system_advanced.service.UserService;
import com.crio.video_rental_system_advanced.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Autowired
    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException
    {

     final String authHeader = request.getHeader("Authorization");
     final String jwt;
     final String userName;

     if(authHeader == null || !authHeader.startsWith("Bearer")) {
         filterChain.doFilter(request, response);
         return;
     }

     jwt = authHeader.substring(7);
     userName = jwtUtil.extractUsername(jwt);

     if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
         UserDetails user = userService.loadUserByUsername(userName);

         if(jwtUtil.validateToken(jwt, user)) {
             UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
             authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
             SecurityContextHolder.getContext().setAuthentication(authenticationToken);
         }
     }

    }
}
