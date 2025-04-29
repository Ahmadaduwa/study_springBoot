// src/main/java/com/study/study/models/Auth/LoginRequest.java
package com.study.study.models.Auth;

public record LoginRequest(
        String username,
        String password
) {}
