package org.selenium.pom.objects;

import org.jetbrains.annotations.Contract;

public class User {

    private String username;
    private String password;
    private String email;

    @Contract(pure = true)
    public User() {}

    @Contract(pure = true)
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Contract(pure = true)
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }
}
