package com.codetally.service;

public interface MeService {
    public String validateMe(String token);
    public String handleCallback(String code);
    public String createCookie(String token);
}
