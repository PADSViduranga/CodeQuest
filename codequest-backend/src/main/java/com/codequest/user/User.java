package com.codequest.user;

import jakarta.persistence.Column;
import jakarta.persistence. *;
import jakarta.persistence.PrePersist;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User{

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column (nullable = false)
    private String password;

    @Column (nullable = false, unique = true)
    private String email;

    @Enumerated (EnumType.STRING)
    @Column (nullable = false, length = 20)
    private UserRole role;

    @Column (nullable = false)
    private boolean enabled;

    @Column (nullable = false)
    private int failedLoginAttempts;

    private LocalDateTime lockedUntil;

    @Column (nullable = false,updatable= false)
    private LocalDateTime createdAt;

    @PrePersist 
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public User(){}

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(int failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }

    public LocalDateTime getLockedUntil() {
        return lockedUntil;
    }

    public void setLockedUntil(LocalDateTime lockedUntil) {
        lockedUntil = lockedUntil;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}