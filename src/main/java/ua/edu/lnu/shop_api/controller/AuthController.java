package ua.edu.lnu.shop_api.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import ua.edu.lnu.shop_api.dto.auth.DefaultUserDetails;
import ua.edu.lnu.shop_api.dto.auth.JwtAuthenticationResponse;
import ua.edu.lnu.shop_api.dto.auth.LoginAuthentication;
import ua.edu.lnu.shop_api.dto.user.UserCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.user.UserResponse;
import ua.edu.lnu.shop_api.service.UserService;
import ua.edu.lnu.shop_api.utils.JwtUtils;

import java.net.URI;
import java.util.Objects;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserCreationUpdateRequest userCreationRequest, UriComponentsBuilder ucb) {
        System.out.println("register "+userCreationRequest.toString());
        UserResponse userResponse = userService.create(userCreationRequest);

        URI location = ucb
                .path("/api/users/{userId}")
                .buildAndExpand(userResponse.id())
                .toUri();
        return ResponseEntity.created(location).body(userResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody @NonNull LoginAuthentication loginRequest) {
        Objects.requireNonNull(loginRequest, "Request user must not be null");
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            DefaultUserDetails userDetails = (DefaultUserDetails) authentication.getPrincipal();
            System.out.println("login "+userDetails.toString());
            System.out.println("login "+userDetails.getRole());
            String jwt = jwtUtils.generateToken(userDetails.getId(), userDetails.getUsername(), userDetails.getRole());
            return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}