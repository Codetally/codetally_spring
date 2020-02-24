package com.codetally.service;

import com.codetally.model.Logline;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public interface GithubWebhookService {
    public void addSingle(InputStream inputStream) throws UnsupportedEncodingException;
}
