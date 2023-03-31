package com.example.firstJWTprojectItAcad.controller;

import com.example.firstJWTprojectItAcad.AuthRequest;
import com.example.firstJWTprojectItAcad.CustomUserDetailsService;
import com.example.firstJWTprojectItAcad.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    JwtUtil jwtUtil;
    @GetMapping("/")
    public String welcome(){
        return "Welcome Home , friend!";
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("invalid username or password");
        }
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(authRequest.getUserName());
        return jwtUtil.generateToken(userDetails);
    }

}
