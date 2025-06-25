package com.giftydelivery.service;
import java.util.List;
import com.giftydelivery.model.User;
import com.giftydelivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // ✅ Register user (admin or normal)
    public User register(User user) {
        return userRepository.save(user);
    }

    // ✅ Login by email and password
    public Optional<User> login(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(u -> u.getPassword().equals(password));
    }

    // ✅ Check if a user is admin (used in controllers)
    public boolean isAdmin(String email) {
        return userRepository.findByEmail(email)
                .map(user -> "ADMIN".equalsIgnoreCase(user.getRole()))
                .orElse(false);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setRole(updatedUser.getRole());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    // ✅ Get user by email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

	public void save(User user) {
		// TODO Auto-generated method stub
		userRepository.save(user); 
		
	}
}
