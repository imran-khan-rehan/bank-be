package com.redmath.Bank.App.auth;

import com.redmath.Bank.App.User.User;
import com.redmath.Bank.App.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) throws Exception {

        if (userRepository.findByEmail(authRequest.getEmail()) == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new AuthResponse("User does not exist"));
        }
        return authService.authenticate(authRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if(!validateUser(user))
        {
            return ResponseEntity
                    .badRequest()
                    .body(new AuthResponse("wrong data or empty "));
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity
                    .badRequest()
                    .body(new AuthResponse("User already exists"));
        }
        user.setRole("USER");
        return ResponseEntity.ok(authService.registerUser(user));
    }
    
    public boolean validateUser(User user)
    {
        if(user.getEmail().trim().length() > 5 && user.getPassword().trim().length() > 7 && user.getName().trim().length() > 2)
        {
            return true;
        }
        return  false;
    }
}
