package com.redmath.Bank.App.auth;

import com.redmath.Bank.App.User.User;
import com.redmath.Bank.App.User.UserRepository;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v2/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) throws Exception {
        User user = userRepository.findByEmail(authRequest.getEmail());
        if (user == null) {
            return ResponseEntity
                    .status(404) // 404 Not Found
                    .body(new AuthResponse("User does not exist"));
        }
        return authService.authenticate(authRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (!validateUser(user)) {
            return ResponseEntity
                    .badRequest()
                    .body(new AuthResponse("Invalid or empty data"));
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity
                    .badRequest()
                    .body(new AuthResponse("User already exists"));
        }
        user.setRole("USER");
        String result = authService.registerUser(user);
        if ("success".equals(result)) {
            return ResponseEntity.status(201).body(new AuthResponse("User registered successfully")); // 201 Created
        } else {
            return ResponseEntity.status(500).body(new AuthResponse("User registration failed")); // 500 Internal Server Error
        }
    }

    private boolean validateUser(User user) {
        return user.getEmail().trim().length() > 5 &&
                user.getPassword().trim().length() > 7 &&
                user.getName().trim().length() > 2;
    }
}
