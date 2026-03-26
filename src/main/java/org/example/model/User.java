package org.example.model;

import jakarta.persistence.*; // For JPA Annotations

@Entity
// this tells JPA that this class is an entity and should be mapped to a database table
@Table(name = "users")

public class User {
    @Id // for making it a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // for auto generating the id
    private Long id ;

    private String username;
    private String password;
    private String role;

    public User() {}

    public User(Long id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getRole() {
        return role;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
