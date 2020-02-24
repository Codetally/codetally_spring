package com.codetally.controller;

import com.codetally.service.GithubWebhookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by greg on 29/08/17.
 */

@WebServlet(value = "/webhook")
public class GithubWebhookController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GithubWebhookServiceImpl githubWebhookService = new GithubWebhookServiceImpl();
        githubWebhookService.addSingle(req.getInputStream());

        resp.setStatus(HttpURLConnection.HTTP_CREATED);
    }
}
