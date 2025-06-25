package com.giftydelivery.controller;
import org.springframework.security.access.prepost.PreAuthorize;
import com.giftydelivery.model.User;
import com.giftydelivery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // ✅ User Registration (can be ADMIN or USER)
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            userService.register(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
        }
    }
    @GetMapping("/all")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(
        @PathVariable("id") Long id,
        @RequestParam("name") String name,
        @RequestParam("email") String email,
        @RequestParam("password") String password,
        @RequestParam("role") String role
    ) {
        Optional<User> existingUser = userService.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setRole(role);
            userService.save(user);
            return ResponseEntity.ok("User updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }




    
    // ✅ Login and return role or invalid credentials
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        Optional<User> loggedInUser = userService.login(user.getEmail(), user.getPassword());
        if (loggedInUser.isPresent()) {
            return ResponseEntity.ok("Login successful as: " + loggedInUser.get().getRole());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
}
