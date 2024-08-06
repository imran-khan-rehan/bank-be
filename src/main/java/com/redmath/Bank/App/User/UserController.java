package com.redmath.Bank.App.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/alls")
    public List<User> getAll() {
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getDetails(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.findUser(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody User user) {

        User existingUser = userService.findUser(userId);
        if (existingUser.getId() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }


        if (!existingUser.getEmail().equals(user.getEmail()) && userService.alreadyRegister(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }


        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());

        if (user.getPassword() != null) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        User updatedUser = userService.save(existingUser);
        return ResponseEntity.ok(updatedUser);
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {

        User existingUser = userService.findUser(userId);
        if (existingUser.getId() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }
}
