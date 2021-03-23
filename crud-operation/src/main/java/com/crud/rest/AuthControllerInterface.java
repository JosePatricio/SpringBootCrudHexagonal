package com.crud.rest;

import com.crud.rest.authPayload.request.LoginRequest;
import com.crud.rest.authPayload.request.SignupRequest;
import com.crud.rest.responseModel.ResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthControllerInterface {
    @PostMapping("/signin")
    ResponseEntity<ResponseModel> authenticateUser(@RequestBody LoginRequest loginRequest);

    @PostMapping("/signup")
    ResponseEntity<ResponseModel> registerUser(@RequestBody SignupRequest signUpRequest);
}
