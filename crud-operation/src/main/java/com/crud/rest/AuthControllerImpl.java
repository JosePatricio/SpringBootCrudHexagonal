package com.crud.rest;

import com.crud.jpa.model.ERole;
import com.crud.jpa.model.Role;
import com.crud.jpa.model.User;
import com.crud.jpa.repositories.RoleRepository;
import com.crud.jpa.repositories.UserRepository;
import com.crud.rest.authPayload.request.LoginRequest;
import com.crud.rest.authPayload.request.SignupRequest;
import com.crud.rest.authPayload.response.JwtResponse;
import com.crud.rest.responseModel.ResponseModel;
import com.crud.security.jwt.JwtUtils;
import com.crud.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthControllerImpl implements AuthControllerInterface {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;

    @Override
    public ResponseEntity<ResponseModel> authenticateUser(LoginRequest loginRequest) {
        ResponseModel response= new ResponseModel();
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            response.setCode(HttpStatus.OK.value());
            response.setStatus(true);
            response.setResponseData(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles));

            return ResponseEntity.ok(response);
        }catch (Exception e){
            response.setCode(HttpStatus.METHOD_FAILURE.value());
            response.setStatus(false);
            return ResponseEntity.ok(response);
        }

    }

    @Override
    public ResponseEntity<ResponseModel> registerUser(SignupRequest signUpRequest) {
        ResponseModel response= new ResponseModel();
       try {
            if (userRepository.existsByUsername(signUpRequest.getUsername())) {
                throw new Exception ("Error: Username is already taken!");
            }

            if (userRepository.existsByEmail(signUpRequest.getEmail())) {
                throw new Exception ("Error: Email is already in use!");
            }

            User user = new User(signUpRequest.getUsername(),
                    signUpRequest.getEmail(),
                    encoder.encode(signUpRequest.getPassword()));

            Set<Role> roles = new HashSet<>();
            Role userRole = roleRepository.findByName(ERole.ROLE_USER).get();
            roles.add(userRole);
            user.setRoles(roles);
            userRepository.save(user);
           response.setMessage("Successfully signed Up");
            response.setCode(HttpStatus.OK.value());
            response.setStatus(true);

            return ResponseEntity.ok(response);

        }catch (Exception e){
           response.setMessage(e.getMessage());
            response.setCode(HttpStatus.METHOD_FAILURE.value());
            response.setStatus(false);
            return ResponseEntity.ok(response);
        }

    }
}
