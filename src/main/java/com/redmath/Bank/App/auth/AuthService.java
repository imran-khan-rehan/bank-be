package com.redmath.Bank.App.auth;

import com.redmath.Bank.App.Balance.BalanceService;
import com.redmath.Bank.App.Jwt.JwtUtil;
import com.redmath.Bank.App.User.User;
import com.redmath.Bank.App.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BalanceService balanceService; // Inject BalanceService

    private String generateUniqueAccountNumber(Long userId) {
        // Generate a random 10-digit number
        Random random = new Random();
        int randomDigits = 1000000000 + random.nextInt(900000000); // Generates a number between 1000000000 and 9999999999

        // Concatenate the user ID with the random number
        return String.format("%010d", randomDigits) + userId;
    }
    private static final int UNAUTHORIZED_STATUS = HttpStatus.UNAUTHORIZED.value();

    public ResponseEntity<?> authenticate(AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(UNAUTHORIZED_STATUS).body(new AuthResponse("password is wrong"));
        }
        User user = userRepository.findByEmail(authRequest.getEmail());
        return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(authRequest.getEmail()), user.getId(), user.getRole(),user.getAccountNumber()));
    }

//    public String registerUser(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//        if (userRepository.findByEmail(user.getEmail()).getId() != null) {
//            balanceService.createBalanceForUser(user.getEmail(), 0.0); // Set initial balance amount as needed
//
//        } else {
//            System.out.println("user not saved");
//        }
//
//        return jwtUtil.generateToken(user.getEmail());
//    }
public String registerUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);

    // Generate a unique account number
    Long userId = user.getId();
    if (userId != null) {
        String accountNumber = generateUniqueAccountNumber(userId);
        user.setAccountNumber(accountNumber);

        // Save user again to update account number
        userRepository.save(user);

        // Proceed with balance creation
        if (userRepository.findByEmail(user.getEmail()).getId() != null) {
            balanceService.createBalanceForUser(user.getEmail(), 0.0); // Set initial balance amount as needed
        } else {
            System.out.println("user not saved");
        }
    }

    return jwtUtil.generateToken(user.getEmail());
}

}
