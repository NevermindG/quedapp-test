package com.quedav1.quedav1back.transaction.application.port.out;

public interface PasswordEncoder {

    String encode(String rawPassword);
    boolean matches(String rawPassword, String encodedPassword);
}
