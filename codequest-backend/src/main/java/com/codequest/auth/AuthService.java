package com.codequest.auth;

import com.codequest.security.JwtService;
import com.codequest.user.User;
import com.codequest.user.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.codequest.user.UserRole;

@Service
public class AuthService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  public AuthService(
      UserRepository userRepository,
      JwtService jwtService) {
    this.userRepository = userRepository;
    this.jwtService = jwtService;
    this.passwordEncoder = new BCryptPasswordEncoder();
  }

  public AuthResponse register(RegisterRequest request) {

    if (userRepository.existsByUsername(request.getUsername())) {
      throw new RuntimeException("Username is already taken");
    }

    if (userRepository.existsByEmail(request.getEmail())) {
      throw new RuntimeException("Email is already registered");
    }

    User user = new User();

    user.setUsername(request.getUsername());
    user.setEmail(request.getEmail());
    user.setPassword(
        passwordEncoder.encode(request.getPassword()));

    user.setRole(UserRole.USER);
    user.setEnabled(true);
    user.setFailedLoginAttempts(0);

    userRepository.save(user);

    return new AuthResponse(
        user.getId(),
        user.getUsername(),
        user.getEmail(),
        user.getRole().name(),
        null,
        "User registered successfully");
  }

  public AuthResponse login(LoginRequest request) {

    User user = userRepository
        .findByEmail(request.getEmail())
        .orElseThrow(() -> new RuntimeException("Invalid email or password"));

    if (!passwordEncoder.matches(
        request.getPassword(),
        user.getPassword()

    )) {

      throw new RuntimeException("Invalid email or password");
    }

    String token = jwtService.generateToken(user.getEmail());
    return new AuthResponse(
        user.getId(),
        user.getUsername(),
        user.getEmail(),
        user.getRole().name(),
        token,
        "User logged in successfully");
  }
}
