package com.redmath.Bank.App.auth;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthResponse {
    private String response;
    private Long id;
    private String role;
    private String accountNumber;
    public AuthResponse(String response) {
        this.response = response;
    }
    public AuthResponse(String response, Long id) {
        this.response = response;
        this.id = id;
    }
    public AuthResponse(String response, Long id, String role,String account) {
        this.response = response;
        this.id = id;
        this.role = role;
        this.accountNumber=account;

    }
}
