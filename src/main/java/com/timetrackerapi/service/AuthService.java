package com.timetrackerapi.service;

import com.timetrackerapi.model.dto.AuthRequestDto;
import com.timetrackerapi.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authManager;

    public String login(AuthRequestDto authRequestDto){
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getLogin(), authRequestDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        return jwtUtils.generateJwtToken(authRequestDto.getLogin());
    }
}
