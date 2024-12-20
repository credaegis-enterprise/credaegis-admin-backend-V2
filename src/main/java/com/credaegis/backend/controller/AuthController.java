package com.credaegis.backend.controller;

import com.credaegis.backend.constant.Constants;
import com.credaegis.backend.http.request.LoginRequest;
import com.credaegis.backend.http.request.MfaLoginRequest;
import com.credaegis.backend.http.response.api.CustomApiResponse;
import com.credaegis.backend.http.response.custom.LoginResponse;
import com.credaegis.backend.http.response.custom.SessionCheckResponse;
import com.credaegis.backend.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = Constants.ROUTEV1+"/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/login")
    public ResponseEntity<CustomApiResponse<LoginResponse>> loginController(@Valid @RequestBody LoginRequest loginRequest,
                                                                            HttpServletRequest request, HttpServletResponse response){
       Boolean mfaEnabled = authService.login(loginRequest,request,response);
        return ResponseEntity.status(HttpStatus.OK).body(new CustomApiResponse<>
                (new LoginResponse(mfaEnabled,Constants.ADMIN,Constants.ORGANIZATION_ACCOUNT_TYPE),"login success",true));
    }


    @PostMapping(path = "/mfa/login")
    public ResponseEntity<CustomApiResponse<LoginResponse>> mfaLoginController(@Valid @RequestBody MfaLoginRequest mfaLoginRequest,
                                                                      HttpServletRequest request, HttpServletResponse
                                                                                  response){

        authService.mfaLogin(mfaLoginRequest,request,response);
        return ResponseEntity.status(HttpStatus.OK).body(
                new CustomApiResponse<>(new LoginResponse(true,Constants.ADMIN,Constants.ORGANIZATION_ACCOUNT_TYPE),
                        "login success",true)
        );
    }


    @GetMapping(path = "/session-check")
    public ResponseEntity<CustomApiResponse<SessionCheckResponse>> sessionCheckController(Authentication authentication){
        if(authentication.isAuthenticated())

            return ResponseEntity.status(HttpStatus.OK).
                    body(new CustomApiResponse<>(
                            new SessionCheckResponse(Constants.ADMIN,Constants.ORGANIZATION_ACCOUNT_TYPE),null,true));
        else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).
                    body(new CustomApiResponse<>(null,"Session expired",false));
    }
}
