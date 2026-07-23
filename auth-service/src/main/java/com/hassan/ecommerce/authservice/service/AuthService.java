package com.hassan.ecommerce.authservice.service;

import com.hassan.ecommerce.authservice.dto.LoginRequest;
import com.hassan.ecommerce.authservice.dto.RegisterRequest;
import com.hassan.ecommerce.authservice.entity.User;
import com.hassan.ecommerce.authservice.exception.EmailAlreadyExistsException;
import com.hassan.ecommerce.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;


    public void register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
             throw new EmailAlreadyExistsException("Email is already registered.");
        }
        String encodedPw = passwordEncoder.encode(request.getPassword());
        User user= new User(request.getFirstName()
                ,request.getLastName()
                , request.getEmail()
                , encodedPw );

        userRepository.save(user);


        // Imp: communicate with wallet service to create a wallet for this new registered user

    }

   public String login(LoginRequest request){
       Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

       User user = (User) authentication.getPrincipal();

       return  jwtService.generateToken(user);
    }
}


