package com.epam.api.builders;

import com.epam.api.model.User;

public class UserBuilder {
    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Integer userStatus;

    public UserBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public UserBuilder setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
        return this;
    }
    public User build() {
        if (id == null) {
            throw new IllegalArgumentException("ID must be set");
        }
        if (username == null) {
            throw new IllegalArgumentException("Username must be set");
        }
        if (firstName == null) {
            throw new IllegalArgumentException("First name must be set");
        }
        if (lastName == null) {
            throw new IllegalArgumentException("Last name must be set");
        }
        if (email == null) {
            throw new IllegalArgumentException("Email must be set");
        }
        if (password == null) {
            throw new IllegalArgumentException("Password must be set");
        }
        if (phone == null) {
            throw new IllegalArgumentException("Phone must be set");
        }
        if (userStatus == null) {
            throw new IllegalArgumentException("User status must be set");
        }
        return new User(id, username, firstName, lastName, email, password, phone, userStatus);
    }
}
