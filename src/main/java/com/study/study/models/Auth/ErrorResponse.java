// src/main/java/com/study/study/models/Auth/ErrorResponse.java
package com.study.study.models.Auth;

// กรณีต้องการส่งข้อความผิดพลาด
public record ErrorResponse(
        String error
) {}
