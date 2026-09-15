package com.codequest.auth;

public class AuthResponse   {

    private Long id;
    private String username;
    private String email;
    private String role;
    private String token;
    private String message;

    public AuthResponse(
        Long id,
        String username,
        String email,
        String role,
        String token,
        String message) {


        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.token = token;
        this.message = message;
    }

    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;  
    }
    public String getRole() {
        return role;
    }
    public String getToken() {
        return token;
    }
    public String getMessage() {
        return message; 
    }

}