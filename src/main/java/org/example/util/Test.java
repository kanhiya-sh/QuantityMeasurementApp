package org.example.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String raw = "1234";
        String encoded = "$2a$10$RaObapPW.trIRYTrp7oglOps/vCxIAwe0JaajE/6ROk8dpGz9Wt1y";

        System.out.println(encoder.matches(raw, encoded));

//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encoded = encoder.encode("1234");
//        System.out.println(encoded);
    }
}