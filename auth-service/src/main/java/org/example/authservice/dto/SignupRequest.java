package org.example.authservice.dto;

public class SignupRequest {
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String mobile;
    private String role;

    public SignupRequest() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getMobile() {
        return mobile;
    }

    public String getRole() {
        return role;
    }

    public void setUsername(String u) {
        this.username = u;
    }

    public void setPassword(String p) {
        this.password = p;
    }

    public void setEmail(String e) {
        this.email = e;
    }

    public void setFullName(String f) {
        this.fullName = f;
    }

    public void setMobile(String m) {
        this.mobile = m;
    }

    public void setRole(String r) {
        this.role = r;
    }
}